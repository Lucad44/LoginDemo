package com.links.login;

import java.util.Set;

public class Role {
    public static final String ADMIN = "admin";

    public static final String USER = "user";

    public static final String SUSPENDED = "suspended";

    private static final Set<String> roles = Set.of(ADMIN, USER, SUSPENDED);

    private Role() {
    }

    public static boolean contains(String role) {
        return roles.contains(role);
    }
}
