package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IItem;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.services.Library;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.Book;

import com.twu.biblioteca.services.Menu;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ItemTest {
    private int checkoutItemId = 1;
    private int returnItemId = 2;

    @Test
    public void actionBooksBook() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        IItem secondBook = books.get(1);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();
        Library library = new Library(books);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapper);

        when(inputReaderWrapperMock.readInt()).thenReturn(checkoutItemId).thenReturn(firstBook.getId());

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapper, subMenu);
        item.action();

        assertEquals(firstBook.getStatus(), "booked");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void actionPrintsBooksTableAndShowsSuccessBookingMessages() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        IItem secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapperMock);

        when(inputReaderWrapperMock.readInt()).thenReturn(checkoutItemId).thenReturn(firstBook.getId());

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapperMock, subMenu);
        item.action();

        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "1. Checkout item"
        );
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "2. Return item"
        );
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "Successfully checked out item."
        );
    }

    @Test
    public void actionDoesNotBookBook() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        IItem secondBook = books.get(1);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenThrow(new IOException());
        Library library = new Library(books);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapper);

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapper, subMenu);
        item.action();

        assertEquals(firstBook.getStatus(), "available");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void actionPrintsBooksTableAndShowsNoSuccessBookingMessage() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        IItem secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenThrow(new IOException()).thenThrow(new IOException());
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapperMock);

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapperMock, subMenu);
        item.action();


        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "1. Checkout item"
        );
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "2. Return item"
        );
    }

    @Test
    public void actionDoesBookNotAlreadyBookedBook() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        firstBook.setStatus("booked");
        IItem secondBook = books.get(1);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId()).thenThrow(new IOException());
        Library library = new Library(books);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapper);

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapper, subMenu);
        item.action();

        assertEquals(firstBook.getStatus(), "booked");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void actionShowsAlreadyBookedMessage() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        firstBook.setStatus("booked");
        IItem secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(checkoutItemId).thenReturn(firstBook.getId());
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        Library library = new Library(books);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapperMock);

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapperMock, subMenu);
        item.action();

        verify(outputWriterWrapperMock, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "1. Checkout item"
        );
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "2. Return item"
        );
        verify(outputWriterWrapperMock, times(1)).writeStringln(
                "Item already checked out."
        );
    }

    @Test
    public void returnItemChangesBookStatus() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        firstBook.setStatus("booked");
        IItem secondBook = books.get(1);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();
        when(inputReaderWrapperMock.readInt()).thenReturn(returnItemId).thenReturn(firstBook.getId());
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapper);

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapper, subMenu);
        item.action();

        assertEquals(firstBook.getStatus(), "available");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void returnItemShowsSuccessMessage() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        firstBook.setStatus("booked");
        IItem secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapper);

        when(inputReaderWrapperMock.readInt()).thenReturn(returnItemId).thenReturn(firstBook.getId());

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapper, subMenu);
        item.action();

        verify(outputWriterWrapper, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapper, times(1)).writeStringln(
                "1. Checkout item"
        );
        verify(outputWriterWrapper, times(1)).writeStringln(
                "2. Return item"
        );
        verify(outputWriterWrapper, times(1)).writeStringln(
                "Successfully returned item."
        );
    }

    @Test
    public void returnItemDoesNotChangeAlreadyReturnedBookStatus() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        firstBook.setStatus("booked");
        IItem secondBook = books.get(1);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapper);

        when(inputReaderWrapperMock.readInt()).thenReturn(returnItemId).thenReturn(firstBook.getId());

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapper, subMenu);
        item.action();

        assertEquals(firstBook.getStatus(), "available");
        assertEquals(secondBook.getStatus(), "available");
    }

    @Test
    public void returnItemShowsAlreadyReturnedBookMessage() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        firstBook.setStatus("available");
        IItem secondBook = books.get(1);

        String expectedBooksInformation = expectedBooksTable(firstBook, secondBook);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapper = Mockito.mock(OutputWriterWrapper.class);
        Menu subMenu = buildSubMenu(library, inputReaderWrapperMock, outputWriterWrapper);

        when(inputReaderWrapperMock.readInt()).thenReturn(returnItemId).thenReturn(firstBook.getId());

        Item item = new Item(1, "List", library, inputReaderWrapperMock, outputWriterWrapper, subMenu);
        item.action();

        verify(outputWriterWrapper, times(1)).writeStringln(expectedBooksInformation);
        verify(outputWriterWrapper, times(1)).writeStringln(
                "1. Checkout item"
        );
        verify(outputWriterWrapper, times(1)).writeStringln(
                "2. Return item"
        );
        verify(outputWriterWrapper, times(1)).writeStringln(
                "Item already returned."
        );
    }

    private String expectedBooksTable(IItem firstBook, IItem secondBook) {
        String expectedBooksInformation = "";
        expectedBooksInformation += firstBook.stringifyData();
        expectedBooksInformation += secondBook.stringifyData();

        return expectedBooksInformation;
    }

    private List<IItem> buildBooks() {
        List<IItem> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Author1", 2000));
        books.add(new Book(2, "Book2", "Author2", 2000));

        return books;
    }

    private Menu buildSubMenu(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper) {
        List<IOption> options = new ArrayList<>();
        IOption checkoutItemOption = new CheckoutItem(1, "Checkout item", library, inputReaderWrapper, outputWriterWrapper);
        IOption returnItemOption = new ReturnItem(2, "Return item", library, inputReaderWrapper, outputWriterWrapper);
        options.add(checkoutItemOption);
        options.add(returnItemOption);

        return new Menu(options);
    }
}
