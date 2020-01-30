package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.SystemWrapper;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.options.BookList;
import com.twu.biblioteca.models.options.Quit;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        System.out.println(welcome.showMessage());

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
            int chosenOptionId = readUserInput();
            String optionContent = menu.executeOption(chosenOptionId);
            System.out.println(optionContent);
        }
    }

    private static int readUserInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }

    private static void showMenu(Menu menu) {
        List<IOption> menuOptions = menu.getOptions();

        for (IOption option : menuOptions) {
            System.out.println(option.getId() + ". " + option.getTitle());
        }
    }

    // TODO: Move to a factory
    private static Menu buildMenu() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Book1 author", 1945));
        books.add(new Book(2, "Book2", "Book2 author", 1945));

        Quit quitOption = new Quit(new SystemWrapper());

        List<IOption> options = new ArrayList<>();
        IOption bookListOption = new BookList(books);
        options.add(bookListOption);
        options.add(quitOption);

        return new Menu(options);
    }
}
