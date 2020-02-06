package com.twu.biblioteca.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Before
    public void setCurrentUser() {
        User user = new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        CurrentUser.set(user);
    }

    @Test
    public void getTitleReturnsTitle() {
        String title = "Pretty title";
        Book book = new Book(1, title, "Author 1", 1944);

        assertEquals(book.getTitle(), title);
    }

    @Test
    public void getAuthorReturnsAuthor() {
        String author = "Author 1";
        Book book = new Book(1, "Book title", author, 1944);

        assertEquals(book.getAuthor(), author);
    }

    @Test
    public void getPublicationYearReturnsPublicationYear() {
        int publicationYear = 1944;
        Book book = new Book(1, "Book title", "Author 1", publicationYear);

        assertEquals(book.getPublicationYear(), publicationYear);
    }

    @Test
    public void getStatusReturnsStatus() {
        Book book = new Book(1, "Book title", "Author 1", 1999);

        assertEquals(book.getStatus(), "available");
    }

    @Test
    public void setStatusSetsNewStatus() {
        Book book = new Book(1, "Book title", "Author 1", 1999);

        book.setStatus("booked");

        assertEquals(book.getStatus(), "booked");
    }

    @Test
    public void stringifyData() {
        Book book = new Book(1, "Book title", "Author 1", 1999);

        assertEquals(
                book.stringifyData(),
                "Id: 1 | Title: Book title | Author: Author 1 | Publication Year: 1999 | Status: available\n"
        );
    }

    @Test
    public void stringifyDataAlreadyBookedAsLibrarian() {
        User bookedByUser = new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        Book book = new Book(1, "Book title", "Author 1", 1999);
        book.setStatus("booked");
        book.setBookedBy(bookedByUser);

        assertEquals(
                book.stringifyData(),
                "Id: 1 | Title: Book title | Author: Author 1 | Publication Year: 1999 | Status: booked | Booked by: xxx-xxxx\n"
        );
    }

    @Test
    public void alreadyBookedReturnsTrue() {
        User bookedByUser = new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        Book book = new Book(1, "Book title", "Author 1", 1999);
        book.setStatus("booked");

        assertEquals(book.alreadyBooked(), true);
    }

    @Test
    public void alreadyBookedReturnsFalse() {
        User bookedByUser = new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        Book book = new Book(1, "Book title", "Author 1", 1999);
        book.setStatus("available");

        assertEquals(book.alreadyBooked(), false);
    }

    @Test
    public void bookedBy() {
        User user = new User(
                "xxx-xxxx",
                "qwerty",
                "librarian",
                "matheus",
                "matheus@tw.com",
                "31 99999-9999"
        );

        Book book = new Book(1, "Book title", "Author 1", 1999);

        book.setBookedBy(user);

        assertEquals(book.getBookedBy(), user);
    }
}
