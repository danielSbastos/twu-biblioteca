package com.twu.biblioteca;

public class Main {

    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        System.out.println(welcome.showMessage());

        showBooksOnTheScreen();
    }

    private static void showBooksOnTheScreen() {
        String[] books = {"Book1", "Book2", "Book3"};
        Library library = new Library(books);
        for (String book : library.getAllBooks()) {
            System.out.println(book);
        }
    }
}
