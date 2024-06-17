package com.links.login.exceptions;

public class UserAlreadyExistsException extends CredentialsException {
    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
