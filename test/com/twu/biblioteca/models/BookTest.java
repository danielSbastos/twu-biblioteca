package com.twu.biblioteca.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void getTitleReturnsTitle() {
        String title = "Pretty title";
        Book book = new Book(title, "Author 1", 1944);

        assertEquals(book.getTitle(), title);
    }

    @Test
    public void getAuthorReturnsAuthor() {
        String author = "Author 1";
        Book book = new Book("Book title", author, 1944);

        assertEquals(book.getAuthor(), author);
    }

    @Test
    public void getPublicationYearReturnsPublicationYear() {
        int publicationYear = 1944;
        Book book = new Book("Book title", "Author 1", publicationYear);

        assertEquals(book.getPublicationYear(), publicationYear);
    }
}
