package com.links.login;

public class User {
    private String username;

    private String password;

    private long id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        setId();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return password.equals(user.password) && username.equals(user.username);
    }

    public int hashCode() {
        long hash = 5381;
        for (int i = 0; i < username.length(); i++) {
            hash = ((hash << 5) + hash) + username.charAt(i);
        }
        return (int) hash;
    }

    public boolean passwordMatch(User user) {
        return password.equals(user.getPassword());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId() {
        id = hashCode();
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 8 || password.length() > 24) {
            return false;
        }
        boolean hasDigit = false;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        return hasDigit && hasUppercase && hasLowercase;
    }

    public boolean setPassword(String password) {
        if (isPasswordValid(password)) {
            this.password = password;
            return true;
        }
        return false;
    }
}
