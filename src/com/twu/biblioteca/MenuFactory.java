package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.lib.SystemWrapper;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.services.Library;
import com.twu.biblioteca.services.Menu;
import com.twu.biblioteca.services.menu_options.CheckoutItem;
import com.twu.biblioteca.services.menu_options.Item;
import com.twu.biblioteca.services.menu_options.Quit;
import com.twu.biblioteca.services.menu_options.ReturnItem;

import java.util.ArrayList;
import java.util.List;

public class MenuFactory {
    public Menu execute() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Book1 author", 1945));
        books.add(new Book(2, "Book2", "Book2 author", 1945));
        Library library = new Library(books);

        InputReaderWrapper inputReaderWrapper = new InputReaderWrapper();
        OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();

        Menu subMenu = buildSubMenu(library, inputReaderWrapper, outputWriterWrapper);
        IOption bookListOption = new Item(library, inputReaderWrapper, outputWriterWrapper, subMenu);
        IOption quitOption = new Quit(new SystemWrapper());

        List<IOption> options = new ArrayList<>();
        options.add(bookListOption);
        options.add(quitOption);

        return new Menu(options);
    }

    private Menu buildSubMenu(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper) {
        List<IOption> options = new ArrayList<>();
        IOption checkoutItemOption = new CheckoutItem(library, inputReaderWrapper, outputWriterWrapper);
        IOption returnItemOption = new ReturnItem(library, inputReaderWrapper, outputWriterWrapper);
        options.add(checkoutItemOption);
        options.add(returnItemOption);

        return new Menu(options);
    }
}
