package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.lib.SystemWrapper;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.options.BooksOption;
import com.twu.biblioteca.models.options.Quit;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        new OutputWriterWrapper().writeStringln(welcome.showMessage());

        Menu menu = buildMenu();

        try {
           executeMainMenu(menu);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input format!");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Please select a valid option");
        }
    }

    private static void executeMainMenu(Menu menu) throws IOException {
        while (true) {
            showMenu(menu);
            int chosenOptionId = new InputReaderWrapper().readInt();
            String optionContent = menu.executeOption(chosenOptionId);
            new OutputWriterWrapper().writeStringln(optionContent);
        }
    }

    private static void showMenu(Menu menu) {
        List<IOption> menuOptions = menu.getOptions();

        for (IOption option : menuOptions) {
            new OutputWriterWrapper().writeStringln(option.getId() + ". " + option.getTitle());
        }
    }

    // TODO: Move to a factory
    private static Menu buildMenu() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Book1 author", 1945));
        books.add(new Book(2, "Book2", "Book2 author", 1945));
        Library library = new Library(books);

        IOption bookListOption = new BooksOption(library, new InputReaderWrapper(), new OutputWriterWrapper());
        IOption quitOption = new Quit(new SystemWrapper());

        List<IOption> options = new ArrayList<>();
        options.add(bookListOption);
        options.add(quitOption);

        return new Menu(options);
    }
}
