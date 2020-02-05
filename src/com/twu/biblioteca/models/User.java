package com.twu.biblioteca.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static List<User> users = new ArrayList<>();

    public String email;
    public String phoneNumber;
    public String name;
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

    public User(String libraryId, String password, String role, String name, String email, String phoneNumber) {
        this.libraryId = libraryId;
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;

        users.add(this);
    }
}
