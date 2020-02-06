package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.SystemWrapper;

public class Quit implements IOption {

    private SystemWrapper systemWrapper;
    private int id;
    private String title;

    public Quit(int id, String title, SystemWrapper systemWrapper) {
        this.systemWrapper = systemWrapper;
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public int getId() {
        return this.id;
    }

    public String action() {
        this.systemWrapper.exit();
        return "Exiting system...";
    }
}
