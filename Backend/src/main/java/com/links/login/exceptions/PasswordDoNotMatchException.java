package com.links.login.exceptions;

public class PasswordDoNotMatchException extends CredentialsException {
    public PasswordDoNotMatchException() {
        super();
    }

    public PasswordDoNotMatchException(String message) {
        super(message);
    }
}
