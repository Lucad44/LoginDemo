package com.links.login;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usersjpa")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = Role.ADMIN;

    public User(String username, String password) {
        this.username = username;
        setPassword(password);
    }

    public boolean setPassword(String password) {
        if (isPasswordValid(password)) {
            this.password = password;
            return true;
        }
        return false;
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
        if (Role.ADMIN.equals(role)) {
            return true;
        }
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
