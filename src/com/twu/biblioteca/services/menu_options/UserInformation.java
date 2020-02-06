package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.models.CurrentUser;
import com.twu.biblioteca.models.User;

public class UserInformation implements IOption {
    private int id;
    private String title;

    public UserInformation(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String action() {
        User currentUser = CurrentUser.get();

        if (currentUser.role == "customer")
            return String.format("Name: %s | Email: %s | Phone number: %s", currentUser.name, currentUser.email, currentUser.phoneNumber);
        return "Action only available for customers";
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
