package com.twu.biblioteca.models;

public class CurrentUser {
    private static User user;

    public static User get() {
        return user;
    }

    public static void set(User currentUser) {
        user = currentUser;
    }
}
