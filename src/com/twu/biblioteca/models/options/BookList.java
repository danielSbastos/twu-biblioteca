package com.twu.biblioteca.models.options;

import com.twu.biblioteca.Library;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.Book;

import java.io.IOException;

public class BookList implements IOption {

    private Library library;
    private InputReaderWrapper inputReaderWrapper;
    private OutputWriterWrapper outputWriterWrapper;

    public BookList(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper) {
        this.library = library;
        this.inputReaderWrapper = inputReaderWrapper;
        this.outputWriterWrapper = outputWriterWrapper;
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

        this.outputWriterWrapper.writeString(information);
        this.outputWriterWrapper.writeString("Do you wish to checkout any book? If yes, enter its id: ");
        checkoutBook(this.library, this.inputReaderWrapper, this.outputWriterWrapper);

        return "Done";
    }

    public int getId() {
        return 1;
    }

    public String getTitle() {
        return "List of Books";
    }

    // TODO: Move to its own class
    private static void checkoutBook(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper) {
        try {
            int bookId = inputReaderWrapper.readInt();
            outputWriterWrapper.writeString("" + bookId);
            library.checkoutBook(bookId);
        } catch (IOException | NumberFormatException e) {}
    }
}
