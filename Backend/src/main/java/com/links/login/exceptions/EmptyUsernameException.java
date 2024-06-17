package com.links.login.exceptions;

public class EmptyUsernameException extends CredentialsException {
    public EmptyUsernameException() {
        super();
    }

    public EmptyUsernameException(String message) {
        super(message);
    }
}
