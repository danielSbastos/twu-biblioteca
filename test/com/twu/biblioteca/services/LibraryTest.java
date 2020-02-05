package com.twu.biblioteca.services;

import com.twu.biblioteca.interfaces.IItem;
import com.twu.biblioteca.models.Book;

import com.twu.biblioteca.models.CurrentUser;
import com.twu.biblioteca.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LibraryTest {

    @Before
    public void setCurrentUser() {
        User.deleteAll();

        User user = new User("xxx-xxxx", "password", "customer");
        CurrentUser.set(user);
    }

    @Test
    public void checkoutItemSuccessfullyCheckoutsTheBook() {
        List<IItem> books = new ArrayList<>();
        Finder finder = new Finder(books);
        Book book = new Book(1, "Book1", "Author1", 1999);
        books.add(book);

        Library library = new Library(books, finder);

        String result = library.checkoutItem(book.getId());

        assertThat(book.getStatus(), is("booked"));
        assertThat(result, is("Successfully checked out item."));
    }

    @Test
    public void checkoutItemFailsToCheckoutItem() {
        List<IItem> books = new ArrayList<>();
        Book book = new Book(1, "Book1", "Author1", 1999);
        book.setStatus("booked");
        books.add(book);

        Finder finder = new Finder(books);
        Library library = new Library(books, finder);

        String result = library.checkoutItem(book.getId());

        assertThat(result, is("Item already checked out."));
    }

    @Test
    public void checkoutItemSetsBookedBy() {
        List<IItem> books = new ArrayList<>();
        Finder finder = new Finder(books);
        Book book = new Book(1, "Book1", "Author1", 1999);
        books.add(book);

        Library library = new Library(books, finder);

        library.checkoutItem(book.getId());

        assertEquals(book.getBookedBy(), CurrentUser.get());
    }

    @Test
    public void returnsBookSuccessfullyReturnsBook() {
        List<IItem> books = new ArrayList<>();
        Book book = new Book(1, "Book1", "Author1", 1999);
        book.setStatus("booked");
        books.add(book);

        Finder finder = new Finder(books);
        Library library = new Library(books, finder);

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

        Finder finder = new Finder(books);
        Library library = new Library(books, finder);

        String result = library.returnItem(book.getId());

        assertThat(result, is("Item already returned."));
    }
}
