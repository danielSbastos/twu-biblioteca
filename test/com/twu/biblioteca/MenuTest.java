package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.options.BooksOption;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class MenuTest {

    @Test
    public void getOptionsReturnOptions()
    {
        List<Book> books = new ArrayList<>();
        Library library = new Library(books);
        IOption bookListOption = new BooksOption(library, new InputReaderWrapper(), new OutputWriterWrapper());

        List<IOption> options = new ArrayList<>();
        options.add(bookListOption);

        Menu menu = new Menu(options);
        List<IOption> expectedOptions = menu.getOptions();

        assertEquals(expectedOptions, options);
    }

    @Test
    public void getOptionByIdWithValidIdReturnsChosenOption()
    {
        List<Book> books = new ArrayList<>();
        Library library = new Library(books);
        IOption option = new BooksOption(library, new InputReaderWrapper(), new OutputWriterWrapper());

        List<IOption> options = new ArrayList<>();
        options.add(option);

        Menu menu = new Menu(options);
        int optionId = 1;
        IOption chosenOption = menu.getOptionById(optionId);

        assertEquals(chosenOption, option);
    }

    // TODO: add #message method to defined error
    @Test(expected = IndexOutOfBoundsException.class)
    public void getOptionByIdWithInvalidIdThrowsAnError()
    {
        List<Book> books = new ArrayList<>();
        Library library = new Library(books);
        IOption option = new BooksOption(library, new InputReaderWrapper(), new OutputWriterWrapper());

        List<IOption> options = new ArrayList<>();
        options.add(option);

        Menu menu = new Menu(options);
        int invalidOptionId = -1;
        menu.getOptionById(invalidOptionId);
    }

    @Test
    public void executeChosenOptionReturnsChosen()
    {
        List<Book> books = new ArrayList<>();
        IOption mockedBookListOption = Mockito.mock(BooksOption.class);
        when(mockedBookListOption.getId()).thenCallRealMethod();

        List<IOption> options = new ArrayList<>();
        options.add(mockedBookListOption);

        Menu menu = new Menu(options);
        menu.executeOption(1);

        verify(mockedBookListOption, times(1)).action();
    }
}
