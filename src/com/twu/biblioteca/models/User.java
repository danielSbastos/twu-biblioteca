package com.twu.biblioteca.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static List<User> users = new ArrayList<>();

    public String libraryId;
    public String password;
    public String role;

    public static List<User> getAll() {
       return users;
    }

    public static User findByLibraryId(String libraryId) {
        for (User user : getAll()) {
            if (user.libraryId.equals(libraryId)) {
                return user;
            }
        }

        return null;
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
