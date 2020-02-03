package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IItem;
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

public class ReturnItemTest {

    @Test
    public void actionReturnsBook() throws IOException {
        List<IItem> books = buildBooks();
        IItem firstBook = books.get(0);
        firstBook.setStatus("available");

        Library library = new Library(books);
        InputReaderWrapper inputReaderWrapperMock = Mockito.mock(InputReaderWrapper.class);
        OutputWriterWrapper outputWriterWrapperMock = Mockito.mock(OutputWriterWrapper.class);
        when(inputReaderWrapperMock.readInt()).thenReturn(firstBook.getId()).thenThrow(new IOException());

        ReturnItem returnItem = new ReturnItem(library, inputReaderWrapperMock, outputWriterWrapperMock);
        String result = returnItem.action();

        assertEquals(firstBook.getStatus(), "available");
        verify(outputWriterWrapperMock, times(1)).writeString(
                "Enter item ID to return: "
        );
        assertEquals(result, "Item already returned.");
    }

    @Test
    public void getTitle() {
        Library library = new Library(new ArrayList<>());

        ReturnItem returnItem = new ReturnItem(library, new InputReaderWrapper(), new OutputWriterWrapper());

        assertEquals(returnItem.getTitle(), "Return item");
    }

    @Test
    public void getId() {
        Library library = new Library(new ArrayList<>());

        ReturnItem returnItem = new ReturnItem(library, new InputReaderWrapper(), new OutputWriterWrapper());

        assertEquals(returnItem.getId(), 2);
    }

    private List<IItem> buildBooks() {
        List<IItem> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Author1", 2000));
        books.add(new Book(2, "Book2", "Author2", 2000));

        return books;
    }
}
