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
    public void findByLibraryIdAndPasswordReturnsUser() {
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

        assertEquals(User.findByLibraryIdAndPassword("yyy-yyyy", "qwerty"), user);
    }

    @Test
    public void findByLibraryIdAndPasswordReturnsNullIfWrongLibraryId() {
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

        assertEquals(User.findByLibraryIdAndPassword("<not-existent-library-id", "qwerty"), null);
    }

    @Test
    public void findByLibraryIdAndPasswordReturnsNullIfWrongPassword() {
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

        assertEquals(User.findByLibraryIdAndPassword("yyy-yyyy", "<wrong-password>"), null);
    }

    @Test
    public void findByLibraryIdAndPasswordReturnsNullIfWrongPasswordAndLibraryId() {
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

        assertEquals(User.findByLibraryIdAndPassword("<wrong-libraryId>", "<wrong-password"), null);
    }

    @Test
    public void isLibrarianReturnsTrue() {
        User user = new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        assertEquals(user.isLibrarian(), true);
    }

    @Test
    public void isLibrarianReturnsFalse() {
        User user = new User(
                "xxx-xxxx",
                "qwerty",
                "customer",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        assertEquals(user.isLibrarian(), false);
    }
}
