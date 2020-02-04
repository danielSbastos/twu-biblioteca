package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LoginHandlerTest {

    @Test
    public void validReturnsTrue() {
        Map credential1 = new HashMap<String, String>();
        credential1.put("username", "matheus");
        credential1.put("password", "qwerty");
        Map credential2 = new HashMap<String, String>();
        credential2.put("username", "daniel");
        credential2.put("password", "zxcvbn");

        List<Map> credentials = new ArrayList<>();
        credentials.add(credential1);
        credentials.add(credential2);

        LoginHandler loginHandler = new LoginHandler(credentials);
        boolean isValidCredential = loginHandler.validateCredentials("matheus", "qwerty");

        assertEquals(isValidCredential, true);
    }

    @Test
    public void validReturnsFalseForWrongPassword() {
        Map credential1 = new HashMap<String, String>();
        credential1.put("username", "matheus");
        credential1.put("password", "qwerty");
        Map credential2 = new HashMap<String, String>();
        credential2.put("username", "daniel");
        credential2.put("password", "zxcvbn");

        List<Map> credentials = new ArrayList<>();
        credentials.add(credential1);
        credentials.add(credential2);

        LoginHandler loginHandler = new LoginHandler(credentials);
        boolean isValidCredential = loginHandler.validateCredentials("matheus", "<wrong-password>");

        assertEquals(isValidCredential, false);
    }

    @Test
    public void validReturnsFalseForWrongUsername() {
        Map credential1 = new HashMap<String, String>();
        credential1.put("username", "matheus");
        credential1.put("password", "qwerty");
        Map credential2 = new HashMap<String, String>();
        credential2.put("username", "daniel");
        credential2.put("password", "zxcvbn");

        List<Map> credentials = new ArrayList<>();
        credentials.add(credential1);
        credentials.add(credential2);

        LoginHandler loginHandler = new LoginHandler(credentials);
        boolean isValidCredential = loginHandler.validateCredentials("<wrong-username>", "qwerty");

        assertEquals(isValidCredential, false);
    }

    @Test
    public void validReturnsFalseForWrongUsernameAndPassword() {
        Map credential1 = new HashMap<String, String>();
        credential1.put("username", "matheus");
        credential1.put("password", "qwerty");
        Map credential2 = new HashMap<String, String>();
        credential2.put("username", "daniel");
        credential2.put("password", "zxcvbn");

        List<Map> credentials = new ArrayList<>();
        credentials.add(credential1);
        credentials.add(credential2);

        LoginHandler loginHandler = new LoginHandler(credentials);
        boolean isValidCredential = loginHandler.validateCredentials("<wrong-username>", "<wrong-password>");

        assertEquals(isValidCredential, false);
    }
}
