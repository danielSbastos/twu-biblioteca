package com.twu.biblioteca.services;

import com.twu.biblioteca.interfaces.IItem;
import com.twu.biblioteca.models.Book;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    @Test
    public void checkoutItemSuccessfullyCheckoutsTheBook() {
        List<IItem> books = new ArrayList<>();
        Book book = new Book(1, "Book1", "Author1", 1999);
        books.add(book);

        Library library = new Library(books);

        String result = library.checkoutItem(book.getId());

        assertThat(book.getStatus(), is("booked"));
        assertThat(result, is("Successfully checked out item."));
    }

    @Test
    public void checkoutItemFailsTocheckoutItem() {
        List<IItem> books = new ArrayList<>();
        Book book = new Book(1, "Book1", "Author1", 1999);
        book.setStatus("booked");
        books.add(book);

        Library library = new Library(books);

        String result = library.checkoutItem(book.getId());

        assertThat(result, is("Item already checked out."));
    }

    @Test
    public void returnsBookSuccessfullyReturnsBook() {
        List<IItem> books = new ArrayList<>();
        Book book = new Book(1, "Book1", "Author1", 1999);
        book.setStatus("booked");
        books.add(book);

        Library library = new Library(books);

        String result = library.returnItem(book.getId());

        assertThat(book.getStatus(), is("available"));
        assertThat(result, is("Successfully returned item."));
    }


    @Test
    public void returnsBookFailsToReturnsBook() {
        List<IItem> books = new ArrayList<>();
        Book book = new Book(1, "Book1", "Author1", 1999);
        book.setStatus("available");
        books.add(book);

        Library library = new Library(books);

        String result = library.returnItem(book.getId());

        assertThat(result, is("Item already returned."));
    }
}
