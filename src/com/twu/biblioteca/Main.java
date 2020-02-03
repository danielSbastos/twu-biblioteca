package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.services.Menu;

import java.io.IOException;


public class Main {
    private static OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();
    private static InputReaderWrapper inputReaderWrapper = new InputReaderWrapper();

    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        outputWriterWrapper.writeStringln(welcome.showMessage());

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
            outputWriterWrapper.writeString(">>> ");
            int chosenOptionId = inputReaderWrapper.readInt();
            String optionContent = menu.executeOption(chosenOptionId);
            outputWriterWrapper.writeStringln(optionContent);
        }
    }

    private static void showMenu(Menu menu) {
        for (IOption option : menu.getOptions()) {
            outputWriterWrapper.writeStringln(option.getId() + ". " + option.getTitle());
        }
    }
}
