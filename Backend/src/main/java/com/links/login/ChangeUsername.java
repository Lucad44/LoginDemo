package com.links.login;

public record ChangeUsername(String newUsername, String confirmUsername) {
    public boolean usernamesMatch() {
        return newUsername.equals(confirmUsername);
    }
}
