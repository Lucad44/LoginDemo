package com.links.login;

import com.links.login.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    private static final String UNF = "User non trovato";
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final UserDao userDao;

    @Autowired
    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUserList() {
        return userDao.getUserList();
    }

    public String createUser(RegisterUser registerUser) throws EmptyUsernameException, PasswordDoNotMatchException, PasswordNotValidException, UserAlreadyExistsException {
        User user = registerUser.getUser();
        if (user == null || user.getUsername() == null) {
            logger.error("Failed to create a new user, invalid or empty username");
            throw new EmptyUsernameException("Username is empty or invalid");
        }
        if (!registerUser.passwordsMatch()) {
            logger.error("User {} failed to create a new user, passwords do not match", user.getId());
            throw new PasswordDoNotMatchException("Passwords do not match");
        }
        if (!user.setPassword(user.getPassword())) {
            logger.error("User {} failed to create a new user, invalid password", user.getId());
            throw new PasswordNotValidException("Invalid password");
        }
        String ret = "User created: " + user;
        if (!userDao.addUser(user)) {
            logger.error("User {} failed to create a new user, user already exists", user.getId());
            throw new UserAlreadyExistsException("User already exists");
        }
        logger.info(ret);
        return ret;
    }

    public String updateUser(ChangeUsername changeUsername) throws LoginException, EmptyUsernameException, UserAlreadyExistsException {
        User user = changeUsername.user();
        try {
            login(user);
        } catch (LoginException e) {
            throw new LoginException("Login failed");
        }
        String newUsername = changeUsername.newUsername();
        if (newUsername == null) {
            logger.error("User {} failed to change username, empty username", user.getId());
            throw new EmptyUsernameException("Empty username");
        }
        if (userDao.containsUser(user)) {
            logger.error("User {} failed to change username to {}, user already exists", user.getId(), newUsername);
            throw new UserAlreadyExistsException("User already exists");
        }
        userDao.updateUser(user, newUsername);
        logger.info("User {} changed username to {}", user.getId(), newUsername);
        return "Username updated successfully to " + newUsername;
    }

    public String deleteUser(User user) throws LoginException {
        try {
            login(user);
        } catch (LoginException e) {
            throw new LoginException("Login failed");
        }
        if (!userDao.removeUser(user)) {
            logger.error("Attempted to remove non-existing user: {}", user);
            throw new UserNotFoundException(UNF);
        }
        String ret = "User deleted: " + user;
        logger.info(ret);
        return ret;
    }

    public String login(User user) throws WrongPasswordException, UserNotFoundException {
        if (user == null) {
            throw new UserNotFoundException(UNF);
        }
        logger.info("User {} attempted login", user.getId());
        if (!userDao.containsUser(user)) {
            logger.error("User {} attempted login, user not found", user.getId());
            throw new UserNotFoundException(UNF);
        }
        User storedUser = userDao.findUser(user.getUsername());
        if (!storedUser.passwordMatch(user)) {
            logger.error("User {} attempted login, incorrect password", user.getId());
            throw new WrongPasswordException("Incorrect password");
        }
        logger.info("User {} logged in successfully", user.getId());
        return "Login successful";
    }

    public String resetPassword(PasswordReset passwordReset) throws UserNotFoundException, PasswordDoNotMatchException, PasswordNotValidException {
        if (!passwordReset.passwordsMatch()) {
            logger.error("User {} failed to reset password, passwords do not match", passwordReset.getUsername());
            throw new PasswordDoNotMatchException("Passwords do not match");
        }
        User user = userDao.findUser(passwordReset.getUsername());
        if (user == null || !userDao.containsUser(user)) {
            logger.error("User {} failed to reset password, user not found", passwordReset.getUsername());
            throw new UserNotFoundException(UNF);
        }
        if (!user.setPassword(passwordReset.getNewPassword())) {
            logger.error("User {} failed to reset password, invalid password", passwordReset.getUsername());
            throw new PasswordNotValidException("Invalid password");
        }
        logger.info("User {} reset password", user.getId());
        return "Password updated successfully";
    }
}
