package com.links.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

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
        return containsUser(user.getUsername());
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

    public float getUserMaxScore(String username) {
        return userRepository.findMaxScoreByUsername(username);
    }

    public int getUserBestTime(String username) {
        return userRepository.findBestTimeByUsername(username);
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

    public Map<String, Float> getMaxScoreList() {
        List<User> userList = getUserList();
        HashMap<String, Float> scoreMap = new HashMap<>();
        for (User user : userList) {
            if (Role.ADMIN.equals(user.getRole())) {
                continue;
            }
            scoreMap.put(user.getUsername(), user.getMaxScore());
        }
        List<Map.Entry<String, Float>> list = new LinkedList<>(scoreMap.entrySet());
        list.sort(Map.Entry.<String, Float>comparingByValue().reversed());
        LinkedHashMap<String, Float> sortedScoreMap = new LinkedHashMap<>();
        for (Map.Entry<String, Float> entry : list) {
            sortedScoreMap.put(entry.getKey(), entry.getValue());
        }
        return sortedScoreMap;
    }

    private String secondsToTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public Map<String, String> getBestTimeList() {
        List<User> userList = getUserList();
        HashMap<String, Integer> timeMap = new HashMap<>();
        for (User user : userList) {
            if (Role.ADMIN.equals(user.getRole())) {
                continue;
            }
            timeMap.put(user.getUsername(), user.getBestTime());
        }
        List<Map.Entry<String, Integer>> list = new LinkedList<>(timeMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        LinkedHashMap<String, String> sortedTimeMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            int entryValue = entry.getValue();
            if (entryValue == 0) {
                sortedTimeMap.put(entry.getKey(), "N/A");
            } else {
                sortedTimeMap.put(entry.getKey(), secondsToTime(entry.getValue()));
            }
        }
        return sortedTimeMap;
    }

    public void setScore(User user, float score) {
        removeUser(user);
        if (user != null) {
            user.setMaxScore(score);
            userRepository.save(user);
        }
    }

    public void setBestTime(User user, int time) {
        removeUser(user);
        if (user != null) {
            user.setBestTime(time);
            userRepository.save(user);
        }
    }
}
