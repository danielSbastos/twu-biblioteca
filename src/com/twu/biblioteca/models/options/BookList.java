package com.twu.biblioteca.models.options;

import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.models.Book;
import java.util.List;

public class BookList implements IOption {

    private List<Book> items;

    public BookList(List<Book> items)
    {
        this.items = items;
    }

    public String getInformation() {
        String information = "";
        for (Book book : this.items) {
            information += String.format("Title: %s | Author: %s | Publication Year: %s\n",
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear());
        }

        return information;
    }

    public int getId() {
        return 1;
    }

    public String getTitle() {
        return "List of Books";
    }
}
