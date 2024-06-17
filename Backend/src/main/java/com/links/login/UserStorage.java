package com.links.login;

import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final String PATH = "users.json";

    private final HashMap<Long, User> users;

    public UserStorage() {
        users = readFromJson();
    }

    public boolean addUser(User user) {
        if (containsUser(user.getId())) {
            return false;
        }
        users.put(user.getId(), user);
        writeToJson();
        return true;
    }

    public User getUser(long id) {
        return users.get(id);
    }

    public User getUser(String username) {
        for (User user : users.values()) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    public boolean removeUser(long id) {
        if (!containsUser(id)) {
            return false;
        }
        users.remove(id);
        writeToJson();
        return true;
    }

    public void updateUser(User user, String newUsername) {
        removeUser(user.getId());
        addUser(new User(newUsername, user.getPassword()));
        writeToJson();
    }

    public boolean containsUser(long id) {
        return users.containsKey(id);
    }

    public boolean containsUser(String username) {
        User user = getUser(username);
        if (user == null) {
            return false;
        }
        return containsUser(user.getId());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : users.values()) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }

    public void writeToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (PrintWriter out = new PrintWriter(PATH)) {
            out.print(gson.toJson(users));
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
    }

    private HashMap<Long, User> readFromJson() {
        HashMap<Long, User> userMap = new HashMap<>();
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(PATH)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            for (Map.Entry<String, com.google.gson.JsonElement> entry : jsonObject.entrySet()) {
                long key = Long.parseLong(entry.getKey());
                User user = gson.fromJson(entry.getValue(), User.class);
                userMap.put(key, user);
            }
        } catch (Exception e) {
            return new HashMap<>();
        }
        return userMap;
    }

    public void clear() {
        users.clear();
        try (FileWriter out = new FileWriter(PATH)) {
            out.write("");
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
    }
}