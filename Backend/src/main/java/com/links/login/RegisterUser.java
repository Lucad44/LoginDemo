package com.links.login;

public class RegisterUser {
    private final User user;

    private final String confirmPassword;

    public RegisterUser(User user, String confirmPassword) {
        this.user = user;
        this.confirmPassword = confirmPassword;
    }

    public User getUser() {
        return user;
    }

    public boolean passwordsMatch() {
        return confirmPassword.equals(user.getPassword());
    }
}
