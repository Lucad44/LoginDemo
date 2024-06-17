package com.links.login.exceptions;

public class WrongPasswordException extends LoginException {
    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
