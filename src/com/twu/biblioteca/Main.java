package com.twu.biblioteca;

import com.twu.biblioteca.factories.SessionFactory;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.CurrentUser;
import com.twu.biblioteca.services.Menu;
import com.twu.biblioteca.factories.MenuFactory;

import java.io.IOException;


public class Main {
    private static OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();
    private static InputReaderWrapper inputReaderWrapper = new InputReaderWrapper();

    public static void main(String[] args) {
        outputWriterWrapper.writeStringln("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");

        SessionHandler sessionHandler = new SessionFactory().execute();
        if (!sessionHandler.login()) return;

        try {
            Menu menu = new MenuFactory().execute();
            executeMainMenu(menu, sessionHandler);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input format!");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Please select a valid option");
        }
    }

    private static void executeMainMenu(Menu menu, SessionHandler sessionHandler) throws IOException {
        while (sessionHandler.anyActiveSession()) {
            outputWriterWrapper.writeStringln("Choose one option to continue:");
            showMenu(menu);
            outputWriterWrapper.writeString(">>> ");

            int chosenOptionId = inputReaderWrapper.readInt();
            String optionContent = menu.executeOption(chosenOptionId);
            outputWriterWrapper.writeStringln(optionContent);
        }

        if (sessionHandler.login())
            executeMainMenu(menu, sessionHandler);
    }

    private static void showMenu(Menu menu) {
        for (IOption option : menu.getOptions()) {
            outputWriterWrapper.writeStringln(option.getId() + ". " + option.getTitle());
        }
    }
}
