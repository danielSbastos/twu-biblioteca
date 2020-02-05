package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.models.CurrentUser;

public class Logout implements IOption {

    public String action() {
        CurrentUser.set(null);
        return "Logged out";
    }

    public int getId() {
        return 4;
    };

    public String getTitle() {
        return "Log out";
    }

}
