package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> listBooks() {
        return this.books;
    }

    public String checkoutBook(int bookId) {
        Book matchedBook = this.books
                               .stream()
                               .filter(book -> book.getId() == bookId)
                               .collect(Collectors.toList())
                               .get(0);

        if (alreadyBooked(matchedBook)) {
            return "Book already booked.";
        }

        matchedBook.setStatus("booked");
        return "Successfully booked book.";
    }

    private boolean alreadyBooked(Book book) {
        return book.getStatus() == "booked";
    }
}
