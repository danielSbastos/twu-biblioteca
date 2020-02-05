package com.twu.biblioteca.interfaces;

import com.twu.biblioteca.models.User;

public interface IItem {
    int getId();

    void setStatus(String status);

    String getStatus();

    String stringifyData();

    void setBookedBy(User user);

    User getBookedBy();
}
