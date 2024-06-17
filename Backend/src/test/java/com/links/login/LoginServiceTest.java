package com.links.login;

import com.links.login.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginServiceTest {
    @Test
    void testCreateUser1() throws EmptyUsernameException, PasswordDoNotMatchException, PasswordNotValidException, UserAlreadyExistsException {
        LoginService loginService = new LoginService();
        User user = new User("username", "password");
        RegisterUser registerUser = new RegisterUser(user, "password");
        String result = loginService.createUser(registerUser);
        assertEquals("Password not valid", result);
    }

    @Test
    void testCreateUser2() throws UserNotFoundException, EmptyUsernameException, PasswordDoNotMatchException, PasswordNotValidException, UserAlreadyExistsException {
        LoginService loginService = new LoginService();
        User user = new User("Marchini", "Abbaggagagga777");
        RegisterUser registerUser = new RegisterUser(user, "Abbaggagagga777");
        String result = loginService.createUser(registerUser);
        assertEquals("User created: " + user, result);
        loginService.deleteUser(user.getId());
    }

    @Test
    void testCreateUser3() throws EmptyUsernameException, PasswordDoNotMatchException, PasswordNotValidException, UserAlreadyExistsException {
        LoginService loginService = new LoginService();
        User user = new User("username", "password1A");
        RegisterUser registerUser = new RegisterUser(user, "password1A");
        String result = loginService.createUser(registerUser);
        assertEquals("User already exists", result);
    }

    @Test
    void testLogin() throws EmptyUsernameException, PasswordDoNotMatchException, PasswordNotValidException, UserAlreadyExistsException, UserNotFoundException, WrongPasswordException {
        LoginService loginService = new LoginService();
        User user = new User("username", "passwordA1");
        RegisterUser registerUser = new RegisterUser(user, "passwordA1");
        loginService.createUser(registerUser);
        String result = loginService.login(user);
        assertEquals("Login succesful", result);
    }

    @Test
    void testResetPassword1() throws UserNotFoundException, PasswordDoNotMatchException {
        LoginService loginService = new LoginService();
        PasswordReset passwordReset = new PasswordReset("username", "passwordA1", "password1A");
        String result = loginService.resetPassword(passwordReset);
        assertEquals("Passwords do not match", result);
    }

    @Test
    void testResetPassword2() throws UserNotFoundException, PasswordDoNotMatchException {
        LoginService loginService = new LoginService();
        PasswordReset passwordReset = new PasswordReset("username", "passwordB1", "passwordB1");
        String result = loginService.resetPassword(passwordReset);
        assertEquals("User not found", result);
    }

    @Test
    void testDeleteUser() throws UserNotFoundException {
        LoginService loginService = new LoginService();
        long id = new User("username", "passwordA1").getId();
        assertEquals("User deleted: " + id, loginService.deleteUser(id));
    }
}
