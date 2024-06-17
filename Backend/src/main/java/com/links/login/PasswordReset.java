package com.links.login;

public class PasswordReset {
    private final String username;

    private final String newPassword;

    private final String confirmPassword;

    public PasswordReset(String username, String newPassword, String confirmPassword) {
        this.username = username;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public boolean passwordsMatch() {
        return newPassword.equals(confirmPassword);
    }
}
