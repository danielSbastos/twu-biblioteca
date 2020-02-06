package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.models.CurrentUser;
import com.twu.biblioteca.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserInformationTest {

    @Before
    public void setCurrentUser() {
        User currentUser = new User(
                "yyy-yyyy",
                "qwerty",
                "customer",
                "daniel",
                "daniel@twu.com",
                "47 99633-2772"
        );

        CurrentUser.set(currentUser);
    }

    @Test
    public void actionShowsUserInformation() {
        UserInformation userInformation = new UserInformation(5, "See my information");

        assertEquals(userInformation.action(), "Name: daniel | Email: daniel@twu.com | Phone number: 47 99633-2772");
    }
}
