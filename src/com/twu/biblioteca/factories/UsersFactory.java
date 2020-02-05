package com.twu.biblioteca.factories;

import com.twu.biblioteca.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersFactory {

    public static List<User> execute() {
        new User(
                "yyy-yyyy",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );
        new User(
                "xxx-xxxx",
                "zxcvb",
                "customer",
                "daniel",
                "daniel@tw.com",
                "47 99666-6666"
        );

        return User.getAll();
    }
}
