package com.twu.biblioteca.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrentUserTest {

    @Test
    public void getAndSet() {
        User user = new User(
                "yyy-yyyy",
                "qwerty",
                "customer",
                "daniel",
                "daniel@tw.com",
                "31 99999-9999"
        );

        CurrentUser.set(user);

        assertEquals(CurrentUser.get(), user);
    }
}
