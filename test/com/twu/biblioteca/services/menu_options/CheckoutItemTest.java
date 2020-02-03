package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.services.Library;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CheckoutItemTest {

    @Test
    public void actionCheckoutsBook() throws IOException {
        List<Book> books = buildBooks();
        Book firstBook = books.get(0);

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId()).thenThrow(new IOException());

        CheckoutItem checkoutItem = new CheckoutItem(library, inputReaderWrapperMock, outputWriterWrapperMock);
        String result = checkoutItem.action();

        assertEquals(firstBook.getStatus(), "booked");
        verify(outputWriterWrapperMock, times(1)).writeString(
                "Enter item ID to checkout: "
        );
        assertEquals(result, "Successfully booked book.");
    }

    @Test
    public void getTitle() {
        Library library = new Library(new ArrayList<>());

        CheckoutItem checkoutItem = new CheckoutItem(library, new InputReaderWrapper(), new OutputWriterWrapper());

        assertEquals(checkoutItem.getTitle(), "Checkout item");
    }

    @Test
    public void getId() {
        Library library = new Library(new ArrayList<>());

        CheckoutItem checkoutItem = new CheckoutItem(library, new InputReaderWrapper(), new OutputWriterWrapper());

        assertEquals(checkoutItem.getId(), 1);
    }

    private List<Book> buildBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Author1", 2000));
        books.add(new Book(2, "Book2", "Author2", 2000));

        return books;
    }
}
