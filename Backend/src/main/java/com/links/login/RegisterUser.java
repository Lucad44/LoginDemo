package com.links.login;

import lombok.Getter;

public class RegisterUser {
    @Getter
    private final User user;

    private final String confirmPassword;

    public RegisterUser(User user, String confirmPassword) {
        this.user = user;
        this.confirmPassword = confirmPassword;
    }

    public boolean passwordsMatch() {
        return confirmPassword.equals(user.getPassword());
    }
}
