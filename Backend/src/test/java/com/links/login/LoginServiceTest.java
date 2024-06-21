package com.links.login;

import com.links.login.exceptions.*;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceTest.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserDao userDao;

    @BeforeEach
    public void setup() {
        assertNotNull(userDao);
    }

    @Test
    void testCreateUser() throws Exception {
        RegisterUser registerUser = new RegisterUser(new User("testuser", "PasswordA1!"), "PasswordA1!");

        // Valid user creation
        String result = loginService.createUser(registerUser);
        assertEquals("User created: User(id=0, username=testuser, password=PasswordA1!, role=user, suspended=false)", result);

        // Empty username
        registerUser.getUser().setUsername(null);
        RegisterUser finalRegisterUser4 = registerUser;
        assertThrows(EmptyUsernameException.class, () -> loginService.createUser(finalRegisterUser4));

        // Forbidden username
        registerUser.getUser().setUsername("admin");
        RegisterUser finalRegisterUser3 = registerUser;
        assertThrows(ForbiddenNameException.class, () -> loginService.createUser(finalRegisterUser3));

        // Passwords not matching
        registerUser = new RegisterUser(new User("testuser", "passAAAAA!1"), "pAAAA!ass2");
        RegisterUser finalRegisterUser2 = registerUser;
        assertThrows(PasswordDoNotMatchException.class, () -> loginService.createUser(finalRegisterUser2));

        // Invalid password
        registerUser = new RegisterUser(new User("testuser", "weak"), "weak");
        RegisterUser finalRegisterUser1 = registerUser;
        assertThrows(PasswordNotValidException.class, () -> loginService.createUser(finalRegisterUser1));

        // User already exists
        registerUser = new RegisterUser(new User("existinguser", "passwordA1!"), "passwordA1!");
        loginService.createUser(registerUser); // Create the user first
        RegisterUser finalRegisterUser = registerUser;
        assertThrows(UserAlreadyExistsException.class, () -> loginService.createUser(finalRegisterUser));
    }

    @Test
    void testUpdateUser() throws Exception {
        ChangeUsername changeUsername = new ChangeUsername("newusername", "newusername");

        // Valid username change
        loginService.createUser(new RegisterUser(new User("testuser", "passwordA1!"), "passwordA1!"));
        loginService.setCurrentUser(new User("testuser", "passwordA1!"));
        String result = loginService.updateUser(changeUsername);
        assertEquals("Username updated successfully to newusername", result);
        loginService.setCurrentUser(new User("testuser", "passwordA1!"));

        // Usernames do not match
        changeUsername = new ChangeUsername("newusername", "newusername2");
        ChangeUsername finalChangeUsername = changeUsername;
        assertThrows(UsernamesDoNotMatchException.class, () -> loginService.updateUser(finalChangeUsername));

        // User already exists
        loginService.createUser(new RegisterUser(new User("existinguser", "passwordA1!"), "passwordA1!"));
        changeUsername = new ChangeUsername("existinguser", "existinguser");
        ChangeUsername finalChangeUsername1 = changeUsername;
        assertThrows(UserAlreadyExistsException.class, () -> loginService.updateUser(finalChangeUsername1));
    }

    @Test
    void testDeleteUser() throws Exception {
        // Valid user deletion
        loginService.createUser(new RegisterUser(new User("testuser", "passwordA1!"), "passwordA1!"));
        loginService.setCurrentUser(new User("testuser", "passwordA1!"));
    }

    @Test
    void testLogin() throws Exception {
        // Valid login
        User validUser = new User("testuser", "passwordA1!");
        loginService.createUser(new RegisterUser(validUser, "passwordA1!"));
        String result = loginService.login(validUser);
        assertEquals("Login successful", result);

        // Suspended user
        User suspendedUser = new User("suspendeduser", "passwordA1!");
        loginService.createUser(new RegisterUser(suspendedUser, "passwordA1!"));
        userDao.suspendUser(suspendedUser); // Simulate user suspension
        assertThrows(SuspendedUserException.class, () -> loginService.login(suspendedUser));
    }

    @Test
    void testResetPassword() throws Exception {
        // Valid password reset
        loginService.createUser(new RegisterUser(new User("testuser", "passwordA1!"), "passwordA1!"));
        PasswordReset passwordReset = new PasswordReset("testuser", "newpasswordA1!", "newpasswordA1!");
        String result = loginService.resetPassword(passwordReset);
        assertEquals("Password updated successfully", result);

        // Passwords do not match
        passwordReset = new PasswordReset("testuser", "newpassword", "differentpassword");
        PasswordReset finalPasswordReset = passwordReset;
        assertThrows(PasswordDoNotMatchException.class, () -> loginService.resetPassword(finalPasswordReset));

        // User not found
        passwordReset = new PasswordReset("nonexistentuser", "newpassword", "newpassword");
        PasswordReset finalPasswordReset1 = passwordReset;
        assertThrows(UserNotFoundException.class, () -> loginService.resetPassword(finalPasswordReset1));

        // Invalid password
        passwordReset = new PasswordReset("testuser", "weak", "weak");
        PasswordReset finalPasswordReset2 = passwordReset;
        assertThrows(PasswordNotValidException.class, () -> loginService.resetPassword(finalPasswordReset2));
    }

    @Test
    void testSuspendUser() throws Exception {
        // Valid user suspension
        loginService.createUser(new RegisterUser(new User("suspendeduser", "passwordA1!"), "passwordA1!"));
        String result = loginService.suspendUser("\"username\":\"ssuspendeduserr\"");
        assertEquals("User suspended successfully", result);

        // User not found
        assertThrows(UserNotFoundException.class, () -> loginService.suspendUser("nonexistefadfadfafntuser"));
    }

    @Test
    void testUnsuspendUser() throws Exception {
        // Valid user unsuspension
        loginService.createUser(new RegisterUser(new User("suspendeduser", "passwordA1!"), "passwordA1!"));
        loginService.suspendUser("\"username\":\"ssuspendeduserr\"");
        String result = loginService.unsuspendUser("\"username\":\"ssuspendeduserr\"");
        assertEquals("User unsuspended successfully", result);
    }
}
