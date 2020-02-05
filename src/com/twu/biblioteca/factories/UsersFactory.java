package com.twu.biblioteca.factories;

import com.twu.biblioteca.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersFactory {

    public static List<User> execute() {
        new User("yyy-yyyy", "qwerty", "librarian");
        new User("xxx-xxxx", "zxcvb", "customer");

        return User.getAll();
    }
}
