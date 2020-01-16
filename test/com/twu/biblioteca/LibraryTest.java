package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LibraryTest {
    @Test
    public void showBooksReturnsListOfAllBooks() {
        String[] books = {"Book1", "Book2", "Book3"};
        Library library = new Library(books);

        assertEquals(library.getAllBooks(), books);
    }
}
