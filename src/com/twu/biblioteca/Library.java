package com.twu.biblioteca;

public class Library {
    private String[] books;

    Library(String[] books) {
        this.books = books;
    }

    public String[] getAllBooks() {
        return this.books;
    }
}
