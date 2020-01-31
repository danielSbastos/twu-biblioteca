package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    @Test
    public void checkoutBookSuccessfullyCheckoutsTheBook() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(1, "Book1", "Author1", 1999);
        books.add(book);

        Library library = new Library(books);

        String result = library.checkoutBook(book.getId());

        assertThat(book.getStatus(), is("booked"));
        assertThat(result, is("Successfully booked book."));
    }

    @Test
    public void checkoutBookFailsToCheckoutBook() {
        List<Book> books = new ArrayList<>();
        Book book = new Book(1, "Book1", "Author1", 1999);
        book.setStatus("booked");
        books.add(book);

        Library library = new Library(books);

        String result = library.checkoutBook(book.getId());

        assertThat(result, is("Book already booked."));
    }
}
