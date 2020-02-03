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
    public void actionBooksBook() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        Book secondBook = books.get(1);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId()).thenThrow(new IOException());
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, new OutputWriterWrapper());
        booksOption.action();

        assertEquals(firstBook.getStatus(), "booked");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void actionPrintsBooksTableAndShowsSuccessBookingMessages() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        Book secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId()).thenThrow(new IOException());
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapperMock);
        booksOption.action();

        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeString(
                "Do you wish to checkout any book? If yes, enter its id or press enter to continue: "
        );
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "Successfully booked book."
        );
    }

    @Test
    public void actionDoesNotBookBook() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        Book secondBook = books.get(1);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenThrow(new IOException());
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, new OutputWriterWrapper());
        booksOption.action();

        assertEquals(firstBook.getStatus(), "available");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void actionPrintsBooksTableAndShowsNoSuccessBookingMessage() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        Book secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenThrow(new IOException());
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapperMock);
        booksOption.action();

        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeString(
                "Do you wish to checkout any book? If yes, enter its id or press enter to continue: "
        );
        verify(outputWriterWrapperMock, times(0)).writeStringln(
                "Successfully booked book."
        );
    }

    @Test
    public void actionDoesBookNotAlreadyBookedBook() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("booked");
        Book secondBook = books.get(1);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId()).thenThrow(new IOException());
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, new OutputWriterWrapper());
        booksOption.action();

        assertEquals(firstBook.getStatus(), "booked");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void actionShowsAlreadyBookedMessage() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("booked");
        Book secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId()).thenThrow(new IOException());
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapperMock);
        booksOption.action();

        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeString(
                "Do you wish to checkout any book? If yes, enter its id or press enter to continue: "
        );
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "Book already booked."
        );
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
    public void returnBookShowsSuccessMessage() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("booked");

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenReturn(firstBook.getId());

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapper);
        booksOption.action();

        verify(outputWriterWrapper, times(1)).writeString(
                "Do you wish to return a book? If yes, enter its id or press enter to continue: "
        );
        verify(outputWriterWrapper, times(1)).writeStringln(
                "Successfully returned book."
        );
    }

    @Test
    public void returnBookDoesNotChangeAlreadyReturnedBookStatus() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("booked");
        Book secondBook = books.get(1);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenReturn(firstBook.getId());

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapper);
        booksOption.action();

        assertEquals(firstBook.getStatus(), "available");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void returnBookShowsAlreadyReturnedBookMessage() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);
        firstBook.setStatus("available");

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenReturn(firstBook.getId());

        BooksOption booksOption = new BooksOption(library, inputReaderWrapperMock, outputWriterWrapper);
        booksOption.action();

        verify(outputWriterWrapper, times(1)).writeString(
                "Do you wish to return a book? If yes, enter its id or press enter to continue: "
        );
        verify(outputWriterWrapper, times(1)).writeStringln(
                "Book was already returned."
        );
    }

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
