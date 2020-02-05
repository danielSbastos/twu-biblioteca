package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.CurrentUser;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.services.Menu;
import com.twu.biblioteca.factories.MenuFactory;
import com.twu.biblioteca.factories.UsersFactory;

import java.io.IOException;
import java.util.List;


public class Main {
    private static OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();
    private static InputReaderWrapper inputReaderWrapper = new InputReaderWrapper();

    public static void main(String[] args) {
        if (!login()) return;

        outputWriterWrapper.writeStringln(new Welcome().showMessage());

        Menu menu = new MenuFactory().execute();

        try {
           executeMainMenu(menu);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input format!");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Please select a valid option");
        }
    }

    private static boolean login() {
        List<User> users = UsersFactory.execute();
        SessionHandler sessionHandler = new SessionHandler(users, inputReaderWrapper, outputWriterWrapper);
        return sessionHandler.login();
    }

    private static void executeMainMenu(Menu menu) throws IOException {
        while (CurrentUser.get() != null) {
            showMenu(menu);
            outputWriterWrapper.writeString(">>> ");
            int chosenOptionId = inputReaderWrapper.readInt();
            String optionContent = menu.executeOption(chosenOptionId);
            outputWriterWrapper.writeStringln(optionContent);
        }

        if (login())
            executeMainMenu(menu);
    }

    private static void showMenu(Menu menu) {
        for (IOption option : menu.getOptions()) {
            outputWriterWrapper.writeStringln(option.getId() + ". " + option.getTitle());
        }
    }
}
