package com.twu.biblioteca;

import  com.twu.biblioteca.models.Book;
import java.util.List;

public class Library {
    private List<Book> books;

    Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> getAllBooks() {
        return this.books;
    }
}
