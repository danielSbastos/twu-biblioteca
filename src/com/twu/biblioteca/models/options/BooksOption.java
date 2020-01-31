package com.twu.biblioteca.models.options;

import com.twu.biblioteca.Library;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.models.Book;

import java.io.IOException;

public class BooksOption implements IOption {

    private Library library;
    private InputReaderWrapper inputReaderWrapper;
    private OutputWriterWrapper outputWriterWrapper;

    public BooksOption(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper) {
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

        this.outputWriterWrapper.writeStringln(information);
        askCheckoutBook(this.library, this.inputReaderWrapper, this.outputWriterWrapper);
        askReturnBook(this.library, this.inputReaderWrapper, this.outputWriterWrapper);

        return "";
    }

    public int getId() {
        return 1;
    }

    public String getTitle() {
        return "List of Books";
    }

    // TODO: Move to its own class
    private static void askCheckoutBook(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper) {
        outputWriterWrapper.writeString("Do you wish to checkout any book? If yes, enter its id or press enter to continue: ");
        try {
            int bookId = inputReaderWrapper.readInt();
            outputWriterWrapper.writeStringln(library.checkoutBook(bookId));
        } catch (IOException | NumberFormatException e) {}
    }

    // TODO: Move to its own class
    private static void askReturnBook(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper) {
        outputWriterWrapper.writeString("Do you wish to return a book? If yes, enter its id or press enter to continue: ");
        try {
            int bookId = inputReaderWrapper.readInt();
            outputWriterWrapper.writeStringln(library.returnBook(bookId));
        } catch (IOException | NumberFormatException e) {}
    }
}
