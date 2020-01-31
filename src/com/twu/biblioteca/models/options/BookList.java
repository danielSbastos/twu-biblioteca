package com.twu.biblioteca.models.options;

import com.twu.biblioteca.Library;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.models.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BookList implements IOption {

    private List<Book> items;

    public BookList(List<Book> items)
    {
        this.items = items;
    }

    public String action() {
        String information = "";
        for (Book book : this.items) {
            information += String.format("Id: %s | Title: %s | Author: %s | Publication Year: %s | Status: %s \n",
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getStatus()
            );
        }

        System.out.println(information);
        System.out.println("Do you wish to checkout any book? If yes, enter its id: ");
        checkoutBook();

        return "Done";
    }

    private void checkoutBook() {
        try {
            int bookId = readUserInput();
            Library library = new Library(this.items);
            library.checkoutBook(bookId);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Okay for now");
        }
    }

    private static int readUserInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }

    public int getId() {
        return 1;
    }

    public String getTitle() {
        return "List of Books";
    }
}
