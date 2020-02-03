package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.services.Library;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.services.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Item implements IOption {

    private Library library;
    private InputReaderWrapper inputReaderWrapper;
    private OutputWriterWrapper outputWriterWrapper;
    private Menu subMenu;

    public Item(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper, Menu subMenu) {
        this.library = library;
        this.inputReaderWrapper = inputReaderWrapper;
        this.outputWriterWrapper = outputWriterWrapper;
        this.subMenu = subMenu;
    }

    public String action() {
        String information = "";
        for (Book book : this.library.listBooks()) {
            information += String.format("Id: %s | Title: %s | Author: %s | Publication Year: %s | Status: %s\n",
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getStatus()
            );
        }

        this.outputWriterWrapper.writeStringln(information);

        try {
            executeSubMenu(this.subMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void executeSubMenu(Menu menu) throws IOException {
        List<IOption> menuOptions = menu.getOptions();

        for (IOption option : menuOptions) {
            new OutputWriterWrapper().writeStringln(option.getId() + ". " + option.getTitle());
        }

        int chosenOptionId = this.inputReaderWrapper.readInt();
        String optionContent = menu.executeOption(chosenOptionId);
        this.outputWriterWrapper.writeStringln(optionContent);
    }

    public int getId() {
        return 1;
    }

    public String getTitle() {
        return "List of Books";
    }
}
