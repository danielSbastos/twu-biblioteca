package com.twu.biblioteca;

import org.junit.Test;
import com.twu.biblioteca.models.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LibraryTest {
    @Test
    public void showBooksReturnsListOfAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book1", "Book1 author", 1945));
        books.add(new Book("Book2", "Book2 author", 1945));

        Library library = new Library(books);

        assertEquals(library.getAllBooks(), books);
    }
}
