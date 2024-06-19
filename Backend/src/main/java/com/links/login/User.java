package com.links.login;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        setPassword(password);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (isPasswordValid(password)) {
            this.password = password;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return id == user.id &&
                username.equals(user.username) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    public long calculateId() {
        long hash = 5381;
        for (char c : username.toCharArray()) {
            hash = ((hash << 5) + hash) + c;
        }
        return hash;
    }

    public boolean passwordMatch(User user) {
        return this.password.equals(user.password);
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
}
