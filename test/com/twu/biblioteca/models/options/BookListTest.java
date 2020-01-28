package com.twu.biblioteca.models.options;

import com.twu.biblioteca.models.Book;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class BookListTest {
    @Test
    public void getInformationReturnsBooksInformation()
    {
        List<Book> books = new ArrayList<>();

        String book1Title = "Book1";
        String book1Author = "Author1";
        int book1PublicationYear = 1945;

        String book2Title = "Book2";
        String book2Author = "Author2";
        int book2PublicationYear = 1945;

        books.add(new Book(book1Title, book1Author, book1PublicationYear));
        books.add(new Book(book2Title, book2Author, book2PublicationYear));

        BookList bookList = new BookList(books);
        String booksInformation = bookList.action();

        String expectedBooksInformation = "";
        expectedBooksInformation +=  String.format("Title: %s | Author: %s | Publication Year: %s\n",
                                                    book1Title,
                                                    book1Author,
                                                    book1PublicationYear);
        expectedBooksInformation +=  String.format("Title: %s | Author: %s | Publication Year: %s\n",
                                                    book2Title,
                                                    book2Author,
                                                    book2PublicationYear);

        assertEquals(booksInformation, expectedBooksInformation);
    }

    @Test
    public void getInformationWithNoBooksReturnsEmptyString()
    {
        List<Book> books = new ArrayList<>();
        BookList bookList = new BookList(books);

        String bookInformation = bookList.action();

        String expectedInformation = "";
        assertEquals(bookInformation, expectedInformation);
    }

    @Test
    public void getTitleReturnsBookListTitle()
    {
        List<Book> bookItems = new ArrayList<>();
        BookList bookList = new BookList(bookItems);

        String title = bookList.getTitle();

        String expectedTitle = "List of Books";
        assertEquals(title, expectedTitle);
    }

    @Test
    public void getIdReturnsBookListId()
    {
        List<Book> bookItems = new ArrayList<>();
        BookList bookList = new BookList(bookItems);

        int id = bookList.getId();

        int expectedId = 1;
        assertEquals(id, expectedId);
    }
}
