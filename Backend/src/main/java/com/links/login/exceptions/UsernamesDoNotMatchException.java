package com.links.login.exceptions;

public class UsernamesDoNotMatchException extends CredentialsException {
    public UsernamesDoNotMatchException() {
        super();
    }

    public UsernamesDoNotMatchException(String message) {
        super(message);
    }
}
