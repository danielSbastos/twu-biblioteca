package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IItem;
import com.twu.biblioteca.services.Library;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.services.Menu;

import java.io.IOException;

public class Item implements IOption {
    private int id;
    private String title;
    private Library library;
    private InputReaderWrapper inputReaderWrapper;
    private OutputWriterWrapper outputWriterWrapper;
    private Menu subMenu;

    public Item(
            int id,
            String title,
            Library library,
            InputReaderWrapper inputReaderWrapper,
            OutputWriterWrapper outputWriterWrapper,
            Menu subMenu
    ) {
        this.id = id;
        this.title = title;
        this.library = library;
        this.inputReaderWrapper = inputReaderWrapper;
        this.outputWriterWrapper = outputWriterWrapper;
        this.subMenu = subMenu;
    }

    public String action() {
        String information = "";
        for (IItem item : this.library.listItems()) {
            information += item.stringifyData();
        }
        this.outputWriterWrapper.writeStringln(information);

        try {
            executeSubMenu(this.subMenu);
        } catch (IOException e) {}

        return "";
    }

    private void executeSubMenu(Menu menu) throws IOException {
        for (IOption option : menu.getOptions()) {
            this.outputWriterWrapper.writeStringln(option.getId() + ". " + option.getTitle());
        }

        this.outputWriterWrapper.writeString(">>> ");
        int chosenOptionId = this.inputReaderWrapper.readInt();
        String optionContent = menu.executeOption(chosenOptionId);
        this.outputWriterWrapper.writeStringln(optionContent);
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
