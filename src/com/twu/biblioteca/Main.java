package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        System.out.println(welcome.showMessage());

        showBooksOnTheScreen();
    }

    private static void showBooksOnTheScreen() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Bodfgjfdgijdfgijdfgjidfgiojoij fsok1", "Book1 author", 1945));
        books.add(new Book("Book2", "Book2 author", 1945));
        Library library = new Library(books);
        for (Book book : library.getAllBooks()) {
            String outputMessage = String.format("Title: %s | Author: %s | Publication Year: %s",
                                                 book.getTitle(),
                                                 book.getAuthor(),
                                                 book.getPublicationYear());
            System.out.println(outputMessage);
        }
    }
}
