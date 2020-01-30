package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public void checkoutBook(int bookId) {
        Book matchedBook = this.books
                               .stream()
                               .filter(book -> book.getId() == bookId)
                               .collect(Collectors.toList())
                               .get(0);

        matchedBook.setStatus("booked");
    }
}
