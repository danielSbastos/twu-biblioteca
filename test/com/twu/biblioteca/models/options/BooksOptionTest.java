package com.twu.biblioteca.models.options;

import com.twu.biblioteca.Library;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.Book;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class BooksOptionTest {
    @Test
    public void actionPrintsBooksTableAndBooksBook() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        Book secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId());
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapperMock);
        String result = booksOption.action();

        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "Do you wish to checkout any book? If yes, enter its id: "
        );
        assertEquals(result, "Successfully booked book.");
        assertEquals(firstBook.getStatus(), "booked");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void actionPrintsBooksTableAndDoesNotBookBook() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        Book secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException());
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapperMock);
        booksOption.action();

        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "Do you wish to checkout any book? If yes, enter its id: "
        );

        assertEquals(firstBook.getStatus(), "available");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void actionPrintsBooksTableAndDoesNotAlreadyBookedBook() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("booked");
        Book secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId());
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapperMock);
        String result = booksOption.action();

        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "Do you wish to checkout any book? If yes, enter its id: "
        );
        assertEquals(result, "Book already booked.");
        assertEquals(firstBook.getStatus(), "booked");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void returnBookChangesBookStatus() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("booked");
        Book secondBook = books.get(1);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenReturn(firstBook.getId());

        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapper);
        booksOption.action();

        assertEquals(firstBook.getStatus(), "available");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void returnBookDoesNotChangeAlreadyReturnedBookStatus() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("booked");
        Book secondBook = books.get(1);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenReturn(firstBook.getId());

        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapper);
        booksOption.action();

        assertEquals(firstBook.getStatus(), "available");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void returnBookDoesNothingWhenUserDoesNotInputId() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("booked");
        Book secondBook = books.get(1);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenThrow(new IOException());

        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapper);
        booksOption.action();

        assertEquals(firstBook.getStatus(), "booked");
        assertEquals(secondBook.getStatus(), "available");
    }

    // TODO: Move to test setUp
    private String expectedBooksTable(Book firstBook, Book secondBook) {
        String expectedBooksInformation = "";
        expectedBooksInformation += String.format("Id: %s | Title: %s | Author: %s | Publication Year: %s | Status: %s\n",
                firstBook.getId(),
                firstBook.getTitle(),
                firstBook.getAuthor(),
                firstBook.getPublicationYear(),
                firstBook.getStatus()
        );
        expectedBooksInformation += String.format("Id: %s | Title: %s | Author: %s | Publication Year: %s | Status: %s\n",
                secondBook.getId(),
                secondBook.getTitle(),
                secondBook.getAuthor(),
                secondBook.getPublicationYear(),
                secondBook.getStatus()
        );

        return expectedBooksInformation;
    }

    private List<Book> buildBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Author1", 2000));
        books.add(new Book(2, "Book2", "Author2", 2000));

        return books;
    }
}
