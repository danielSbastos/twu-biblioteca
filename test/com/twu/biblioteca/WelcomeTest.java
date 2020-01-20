package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WelcomeTest {
    @Test
    public void showMessageReturnsWelcomeMessage() {
        Welcome welcome = new Welcome();

        String expectedMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n" +
                                 "Choose one option to continue:";

        assertEquals(welcome.showMessage(), expectedMessage);
    }
}
