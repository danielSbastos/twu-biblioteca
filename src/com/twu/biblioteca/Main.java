package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.services.Menu;

import java.io.IOException;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Welcome welcome = new Welcome();

        new OutputWriterWrapper().writeStringln(welcome.showMessage());

        Menu menu = new MenuFactory().execute();

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
            new OutputWriterWrapper().writeString(">>> ");
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
}
