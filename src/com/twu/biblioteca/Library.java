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
        Book book = findBook(bookId);

        if (alreadyBooked(book)) {
            return "Book already booked.";
        }

        book.setStatus("booked");
        return "Successfully booked book.";
    }

    public String returnBook(int bookId) {
        Book book = findBook(bookId);

        if (alreadyReturned(book)) {
            return "Book was already returned.";
        }

        book.setStatus("available");
        return "Successfully returned book.";
    }

    private Book findBook(int bookId) {
        return this.books
               .stream()
               .filter(book -> book.getId() == bookId)
               .collect(Collectors.toList())
               .get(0);
    }

    private boolean alreadyBooked(Book book) {
        return book.getStatus() == "booked";
    }

    private boolean alreadyReturned(Book book) {
        return book.getStatus() == "available";
    }
}
