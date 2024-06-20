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

    private User currentUser = null;

    private final UserDao userDao;

    @Autowired
    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUserList() {
        return userDao.getUserList();
    }

    public String createUser(RegisterUser registerUser) throws EmptyUsernameException, PasswordDoNotMatchException, PasswordNotValidException, UserAlreadyExistsException, ForbiddenNameException {
        User user = registerUser.getUser();
        if (user == null || user.getUsername() == null) {
            logger.error("Failed to create a new user, invalid or empty username");
            throw new EmptyUsernameException("Username is empty or invalid");
        }
        if (!Role.ADMIN.equals(user.getRole()) && Role.contains(user.getUsername())) {
            logger.error("User {} failed to create a new user, username forbidden", user.getId());
            throw new ForbiddenNameException("Username forbidden");
        }
        if (!registerUser.passwordsMatch()) {
            logger.error("User {} failed to create a new user, passwords do not match", user.getId());
            throw new PasswordDoNotMatchException("Passwords do not match");
        }
        user.setRole(userDao.getUserRole(user.getUsername()));
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

    public String updateUser(ChangeUsername changeUsername) throws EmptyUsernameException, UserAlreadyExistsException, LoginException, UsernamesDoNotMatchException {
        if (currentUser == null) {
            logger.error("User failed to change username, login required");
            throw new LoginException("Login required");
        }
        long currentId = currentUser.getId();
        if (!changeUsername.usernamesMatch()) {
            logger.error("User {} failed to change username, usernames do not match", currentUser.getId());
            throw new UsernamesDoNotMatchException("Passwords do not match");
        }
        String newUsername = changeUsername.newUsername();
        if (newUsername == null) {
            logger.error("User {} failed to change username, empty username", currentId);
            throw new EmptyUsernameException("Empty username");
        }
        if (userDao.containsUser(newUsername)) {
            logger.error("User {} failed to change username to {}, user already exists", currentId, newUsername);
            throw new UserAlreadyExistsException("User already exists");
        }
        userDao.updateUser(currentUser, newUsername);
        currentUser.setUsername(newUsername);
        logger.info("User {} changed username to {}", currentUser, newUsername);
        return "Username updated successfully to " + newUsername;
    }

    public String deleteUser(User user) throws LoginException {
        currentUser = user;
        if (currentUser == null) {
            logger.error("User failed to delete account, login required");
            throw new LoginException("Login required");
        }
        if (!userDao.removeUser(currentUser)) {
            logger.error("Attempted to remove non-existing user: {}", currentUser);
            throw new UserNotFoundException(UNF);
        }
        String ret = "User deleted: " + currentUser;
        currentUser = null;
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
        user.setRole(userDao.getUserRole(user.getUsername()));
        currentUser = user;
        logger.info("User {} logged in successfully", user.getId());
        return "Login successful";
    }

    public String resetPassword(PasswordReset passwordReset) throws UserNotFoundException, PasswordDoNotMatchException, PasswordNotValidException {
        if (!passwordReset.passwordsMatch()) {
            logger.error("User {} failed to reset password, passwords do not match", passwordReset.getUsername());
            throw new PasswordDoNotMatchException("Passwords do not match");
        }
        User user = userDao.findUser(passwordReset.getUsername());
        if (!userDao.containsUser(user)) {
            logger.error("User {} failed to reset password, user not found", passwordReset.getUsername());
            throw new UserNotFoundException(UNF);
        }
        if (!user.setPassword(passwordReset.getNewPassword())) {
            logger.error("User {} failed to reset password, invalid password", passwordReset.getUsername());
            throw new PasswordNotValidException("Invalid password");
        }
        userDao.updatePassword(passwordReset.getUsername(), passwordReset.getNewPassword());
        logger.info("User {} reset password", user.getId());
        return "Password updated successfully";
    }
}
