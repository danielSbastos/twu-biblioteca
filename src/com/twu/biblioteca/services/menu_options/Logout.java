package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.models.CurrentUser;

public class Logout implements IOption {

    private int id;
    private String title;

    public Logout(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String action() {
        CurrentUser.set(null);
        return "Logged out";
    }

    public int getId() {
        return this.id;
    };

    public String getTitle() {
        return this.title;
    }

}
