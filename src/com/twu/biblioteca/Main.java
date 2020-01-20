package com.twu.biblioteca;

import com.twu.biblioteca.models.Book;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    // main does the dependency injection
    // e.g. new Screen, new Display...
    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        System.out.println(welcome.showMessage());

        showMenu();

        try {
            int chosenOption = readUserInput();
            executeChosenOption(chosenOption);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Invalid input format!");
        }
    }

    private static int readUserInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
    }

    private static void executeChosenOption(int option)
    {
        if(option == 1)
            showBooks();
    }

    private static void showMenu() {
        Menu menu = new Menu(new String[]{"1. List of Books"});
        String options = String.join("\n", menu.getOptions());
        System.out.println(options);
    }

    private static void showBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book1", "Book1 author", 1945));
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
