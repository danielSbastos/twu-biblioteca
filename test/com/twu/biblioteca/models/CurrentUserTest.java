package com.twu.biblioteca.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrentUserTest {

    @Test
    public void getAndSet() {
        User user = new User("aaa-aaaa", "password", "customer");

        CurrentUser.set(user);

        assertEquals(CurrentUser.get(), user);
    }
}
