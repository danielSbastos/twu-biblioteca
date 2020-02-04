package com.twu.biblioteca;

import java.util.List;
import java.util.Map;

public class LoginHandler {

    private List<Map> credentials;

    public  LoginHandler(List<Map> credentials)
    {
        this.credentials = credentials;
    }

    public boolean validateCredentials(String username, String password)
    {
        for (Map<String, String> map: this.credentials) {
            if(map.get("username").equals(username) && map.get("password").equals(password))
                return true;
        }

        return false;
    }

}
