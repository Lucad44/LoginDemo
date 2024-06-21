package com.links.login.exceptions;

public class SuspendedUserException extends CredentialsException {
    public SuspendedUserException() {
        super();
    }

    public SuspendedUserException(String message) {
        super(message);
    }
}
