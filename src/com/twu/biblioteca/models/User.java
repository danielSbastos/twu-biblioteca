package com.twu.biblioteca.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private static List<User> users = new ArrayList<>();

    public String libraryId;
    public String password;
    private String role;

    public static List<User> getAll() {
       return users;
    }

    public static User findByLibraryId(String libraryId) {
        try {
            return getAll()
                    .stream()
                    .filter(user -> user.libraryId == libraryId)
                    .collect(Collectors.toList())
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void deleteAll() {
        users = new ArrayList<>();
    }

    public User(String libraryId, String password, String role) {
        this.libraryId = libraryId;
        this.password = password;
        this.role = role;

        users.add(this);
    }
}
