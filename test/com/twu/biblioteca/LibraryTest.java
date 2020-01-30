package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    @Test
    public void checkoutBookCheckoutsTheBook() {
        List<Book> books = new ArrayList<>();
        Book book = new Book("Book1", "Author1", 1999);
        books.add(book);

        Library library = new Library(books);

        String bookName = "Book1";
        library.checkoutBook(bookName);

        assertThat(book.getStatus(), is("booked"));
    }
}
