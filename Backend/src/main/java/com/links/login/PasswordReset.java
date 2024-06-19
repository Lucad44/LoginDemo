package com.links.login;

import lombok.Getter;

public class PasswordReset {
    @Getter
    private final String username;

    @Getter
    private final String newPassword;

    private final String confirmPassword;

    public PasswordReset(String username, String newPassword, String confirmPassword) {
        this.username = username;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public boolean passwordsMatch() {
        return newPassword.equals(confirmPassword);
    }
}
