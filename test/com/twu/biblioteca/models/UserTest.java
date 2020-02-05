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
        User user1 = new User("111-1111", "password", "customer");
        User user2 = new User("222-2222", "password", "customer");
        User user3 = new User("333-3333", "password", "librarian");

        List<User> expectedAllUsers = new ArrayList<>();
        expectedAllUsers.add(user1);
        expectedAllUsers.add(user2);
        expectedAllUsers.add(user3);

        assertEquals(User.getAll(), expectedAllUsers);
    }

    @Test
    public void findByLibraryIdReturnsUser() {
        User user = new User("111-1111", "password", "customer");
        new User("222-2222", "password", "customer");
        new User("333-3333", "password", "librarian");

        assertEquals(User.findByLibraryId("111-1111"), user);
    }

    @Test
    public void findByLibraryIdReturnsNull() {
        new User("111-1111", "password", "customer");
        new User("222-2222", "password", "customer");
        new User("333-3333", "password", "librarian");

        assertEquals(User.findByLibraryId("<not-existent"), null);
    }
}
