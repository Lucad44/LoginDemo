package com.links.login.exceptions;

public class UserNotFoundException extends LoginException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
