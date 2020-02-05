package com.twu.biblioteca.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Before
    public void setUp() {
        User.deleteAll();
    }

    @Test
    public void getAll() {
        User user1 = new User(
                "yyy-yyyy",
                "qwerty",
                "customer",
                "daniel",
                "daniel@tw.com",
                "31 99999-9999"
        );

        User user2 = new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );


        List<User> expectedAllUsers = new ArrayList<>();
        expectedAllUsers.add(user1);
        expectedAllUsers.add(user2);

        assertEquals(User.getAll(), expectedAllUsers);
    }

    @Test
    public void findByLibraryIdReturnsUser() {
        User user = new User(
                "yyy-yyyy",
                "qwerty",
                "customer",
                "daniel",
                "daniel@tw.com",
                "31 99999-9999"
        );
        new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        assertEquals(User.findByLibraryId("yyy-yyyy"), user);
    }

    @Test
    public void findByLibraryIdReturnsNull() {
        User user = new User(
                "yyy-yyyy",
                "qwerty",
                "customer",
                "daniel",
                "daniel@tw.com",
                "31 99999-9999"
        );
        new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        assertEquals(User.findByLibraryId("<not-existent"), null);
    }
}
