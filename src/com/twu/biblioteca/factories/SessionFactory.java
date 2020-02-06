package com.twu.biblioteca.factories;

import com.twu.biblioteca.SessionHandler;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.User;

import java.util.ArrayList;
import java.util.List;

public class SessionFactory {
    public SessionHandler execute() {
        InputReaderWrapper inputReaderWrapper = new InputReaderWrapper();
        OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();

        List<User> users = this.buildUsers();
        return new SessionHandler(users, inputReaderWrapper, outputWriterWrapper);
    }

    private List<User> buildUsers() {
        List<User> users = new ArrayList<>();
        users.add(
                new User(
                        "yyy-yyyy",
                        "qwerty",
                        "librarian",
                        "matheus",
                        "matheus@tw.com",
                        "31 99999-9999"
                )
        );
        users.add(
                new User(
                        "xxx-xxxx",
                        "zxcvb",
                        "customer",
                        "daniel",
                        "daniel@tw.com",
                        "47 99666-6666"
                )
        );

        return users;
    }
}
