package com.links.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    private final UserRepository userRepository;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(User user) {
        if (containsUser(user)) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean removeUser(User user) {
        if (!containsUser(user)) {
            return false;
        }
        userRepository.deleteByUsername(user.getUsername());
        return true;
    }

    public void updateUser(User user, String newUsername) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(newUsername);
            userRepository.save(existingUser);
        }
    }

    public void updatePassword(String username, String newPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }

    public boolean containsUser(User user) {
        if (user == null) {
            return false;
        }
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    public boolean containsUser(String username) {
        if (username == null) {
            return false;
        }
        return userRepository.findByUsername(username).isPresent();
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public String getUserRole(String username) {
        return userRepository.findRoleByUsername(username);
    }

    public void suspendUser(User user) {
        if (user != null) {
            user.setSuspended(true);
            userRepository.save(user);
        }
    }

    public void unsuspendUser(User user) {
        if (user != null) {
            user.setSuspended(false);
            userRepository.save(user);
        }
    }

    public boolean isUserSuspended(String username) {
        return userRepository.isUserSuspended(username);
    }

    public String objectToUsername(String object) {
        return object.substring(13, object.length() - 2);
    }
}
