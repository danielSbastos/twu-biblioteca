package com.twu.biblioteca.interfaces;

public interface IItem {
    int getId();

    void setStatus(String status);

    String getStatus();

    String stringifyData();
}
