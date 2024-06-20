package com.links.login.exceptions;

public class ForbiddenNameException extends CredentialsException {
    public ForbiddenNameException() {
        super();
    }

    public ForbiddenNameException(String message) {
        super(message);
    }
}
