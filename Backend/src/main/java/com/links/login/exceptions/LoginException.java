package com.links.login.exceptions;

public class LoginException extends CredentialsException {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }
}
