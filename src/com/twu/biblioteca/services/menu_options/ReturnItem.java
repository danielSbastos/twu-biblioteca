package com.twu.biblioteca.services.menu_options;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.services.Library;

import java.io.IOException;

public class ReturnItem implements IOption {
    private int id;
    private String title;
    private Library library;
    private InputReaderWrapper inputReaderWrapper;
    private OutputWriterWrapper outputWriterWrapper;

    public ReturnItem(
            int id,
            String title,
            Library library,
            InputReaderWrapper inputReaderWrapper,
            OutputWriterWrapper outputWriterWrapper
    ) {
        this.id = id;
        this.title = title;
        this.library = library;
        this.inputReaderWrapper = inputReaderWrapper;
        this.outputWriterWrapper = outputWriterWrapper;
    }

    public String getTitle() {
        return this.title;
    }

    public int getId() {
        return this.id;
    }

    public String action() {
        this.outputWriterWrapper.writeString("Enter item ID to return: ");

        try {
            int bookId = this.inputReaderWrapper.readInt();
            return this.library.returnItem(bookId);
        } catch (IOException | NumberFormatException e) {}

        return "";
    }
}
