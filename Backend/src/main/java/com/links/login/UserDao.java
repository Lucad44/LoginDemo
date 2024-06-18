package com.links.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean addUser(User user) {
        if (containsUser(user)) {
            return false;
        }
        String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword());
        return true;
    }

    public boolean removeUser(User user) {
        if (!containsUser(user)) {
            return false;
        }
        String sql = "DELETE FROM users WHERE username = ?";
        jdbcTemplate.update(sql, user.getUsername());
        return true;
    }

    public void updateUser(User user, String newUsername) {
        String sql = "UPDATE users SET username = ? WHERE username = ?";
        jdbcTemplate.update(sql, newUsername, user.getUsername());
    }

    public boolean containsUser(User user) {
        if (user == null) {
            return false;
        }
        return findUser(user.getUsername()) != null;
    }

    public User findUser(String username) {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<User>(User.class));
        } catch (Exception e) {
            logger.error("Failed to find user {}: {}", username, e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        try {
            String sql = "SELECT * FROM users";
            List<User> ret = Objects.requireNonNull(jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class)));
            return ret.toString();
        } catch (Exception e) {
            logger.error("Failed to get users: {}", e.getMessage());
            return "No users found";
        }
    }
}