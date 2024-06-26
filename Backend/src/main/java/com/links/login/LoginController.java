package com.links.login;

import com.links.login.exceptions.CredentialsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "Get all users",
            description = "Returns a list of all users")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserList() {
        try {
            return ResponseEntity.ok(loginService.getUserList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @Operation(summary = "Register a new user",
            description = " Returns a message indicating whether the user was created or not")
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Parameter(description = "The user to be created",required = true)
                                             @RequestBody RegisterUser registerUser) {
        try {
            return ResponseEntity.ok(loginService.createUser(registerUser));
        } catch (CredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @Operation(summary = "Update a user's username",
            description = "Returns a message indicating whether the username was updated or not")
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@Parameter(description = "The user to be updated",required = true)
                                             @RequestBody ChangeUsername changeUsername, HttpSession session) {
        try {
            return ResponseEntity.ok(loginService.updateUser(changeUsername));
        } catch (CredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @Operation(summary = "Delete a user",
            description = "Returns a message indicating whether the user was deleted or not")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser() {
        try {
            return ResponseEntity.ok(loginService.deleteUser());
        } catch (CredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @Operation(summary = "Login a user",
            description = "Returns a message indicating whether the user was logged in or not")
    @PostMapping("/login")
    public ResponseEntity<String> login(@Parameter(description = "The user to be logged in",required = true)
                                        @RequestBody User user) {
        try {
            return ResponseEntity.ok(loginService.login(user));
        } catch (CredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @Operation(summary = "Reset a user's password",
            description = "Returns a message indicating whether the password was reset or not")
    @PutMapping("/reset")
    public ResponseEntity<String> resetPassword(@Parameter (description = "The password reset",required = true)
                                                @RequestBody PasswordReset passwordReset) {
        try {
            return ResponseEntity.ok(loginService.resetPassword(passwordReset));
        } catch (CredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
