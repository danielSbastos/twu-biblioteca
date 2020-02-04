package com.twu.biblioteca.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersFactory {

    public static List<Map> buildUsers()
    {
        List<Map> users = new ArrayList<>();
        users.add(
                new HashMap<String, String>() {{
                    put("username", "matheus");
                    put("password", "qwerty");
                }}
        );

        users.add(
                new HashMap<String, String>() {{
                    put("username", "daniel");
                    put("password", "zxcvb");
                }}
        );

        return users;
    }
}
