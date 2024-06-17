package com.links.login.exceptions;

public class PasswordNotValidException extends CredentialsException {
    public PasswordNotValidException() {
        super();
    }

    public PasswordNotValidException(String message) {
        super(message);
    }
}
