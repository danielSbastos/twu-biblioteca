package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.SessionHandler;
import com.twu.biblioteca.interfaces.IOption;

public class Logout implements IOption {
    private SessionHandler sessionHandler;
    private int id;
    private String title;

    public Logout(int id, String title, SessionHandler sessionHandler) {
        this.id = id;
        this.title = title;
        this.sessionHandler = sessionHandler;
    }

    public String action() {
        sessionHandler.logout();
        return "Logged out";
    }

    public int getId() {
        return this.id;
    };

    public String getTitle() {
        return this.title;
    }

}
