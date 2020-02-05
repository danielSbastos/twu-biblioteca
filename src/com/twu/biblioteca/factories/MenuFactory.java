package com.twu.biblioteca.factories;

import com.twu.biblioteca.interfaces.IItem;
import com.twu.biblioteca.interfaces.IOption;
import com.twu.biblioteca.lib.InputReaderWrapper;
import com.twu.biblioteca.lib.OutputWriterWrapper;
import com.twu.biblioteca.lib.SystemWrapper;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.models.User;
import com.twu.biblioteca.services.Library;
import com.twu.biblioteca.services.Menu;
import com.twu.biblioteca.services.menu_options.*;

import java.util.ArrayList;
import java.util.List;

public class MenuFactory {
    public Menu execute() {
        Library booksLibrary = buildBooksLibrary();
        Library moviesLibrary = buildMoviesLibrary();

        InputReaderWrapper inputReaderWrapper = new InputReaderWrapper();
        OutputWriterWrapper outputWriterWrapper = new OutputWriterWrapper();

        Menu subMenuBooks = buildSubMenu(booksLibrary, inputReaderWrapper, outputWriterWrapper);
        Menu subMenuMovies = buildSubMenu(moviesLibrary, inputReaderWrapper, outputWriterWrapper);
        IOption bookListOption = new Item(
                1, "List of Books", booksLibrary, inputReaderWrapper, outputWriterWrapper, subMenuBooks
        );
        IOption movieListOption = new Item(
                2, "List of Movies", moviesLibrary, inputReaderWrapper, outputWriterWrapper, subMenuMovies
        );
        IOption quitOption = new Quit(new SystemWrapper());
        IOption logoutOption = new Logout();
        IOption userInformation = new UserInformation();

        List<IOption> options = new ArrayList<>();
        options.add(bookListOption);
        options.add(movieListOption);
        options.add(quitOption);
        options.add(logoutOption);
        options.add(userInformation);

        return new Menu(options);
    }

    private Library buildMoviesLibrary() {
        List<IItem> movies = new ArrayList<>();
        movies.add(new Movie(1, "Movie 1", 2000, "Director 1", 10));
        movies.add(new Movie(2, "Movie 2", 2000, "Director 2", 1));
        return new Library(movies);
    }

    private Library buildBooksLibrary() {
        List<IItem> books = new ArrayList<>();
        books.add(new Book(1, "Book1", "Book1 author", 1945));
        books.add(new Book(2, "Book2", "Book2 author", 1945));
        return new Library(books);
    }

    private Menu buildSubMenu(Library library, InputReaderWrapper inputReaderWrapper, OutputWriterWrapper outputWriterWrapper) {
        List<IOption> options = new ArrayList<>();
        IOption checkoutItemOption = new CheckoutItem(library, inputReaderWrapper, outputWriterWrapper);
        IOption returnItemOption = new ReturnItem(library, inputReaderWrapper, outputWriterWrapper);
        options.add(checkoutItemOption);
        options.add(returnItemOption);
        options.add(new Quit(new SystemWrapper()));
        options.add(new Logout());

        return new Menu(options);
    }
}
