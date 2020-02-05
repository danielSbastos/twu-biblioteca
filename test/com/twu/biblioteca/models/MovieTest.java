package com.twu.biblioteca.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void getId() {
        Movie movie = new Movie(1, "Movie", 1999, "Director", 1);

        assertEquals(movie.getId(), 1);
    }

    @Test
    public void setStatus() {
        Movie movie = new Movie(1, "Movie", 1999, "Director", 1);

        assertEquals(movie.getStatus(), "available");

        movie.setStatus("booked");

        assertEquals(movie.getStatus(), "booked");
    }

    @Test
    public void getStatus() {
        Movie movie = new Movie(1, "Movie", 1999, "Director", 1);

        assertEquals(movie.getStatus(), "available");
    }

    @Test
    public void stringifyData() {
        Movie movie = new Movie(1, "Movie", 1999, "Director", 1);

        String expectedStringifyData = "Id: 1 | Name: Movie | Year: 1999 | Director: Director | Rating: 1 | Status: available\n";

        assertEquals(movie.stringifyData(), expectedStringifyData);
    }

    @Test
    public void bookedBy() {
        User user = new User("aaaa", "aaaa", "customer");
        Movie movie = new Movie(1, "Movie", 1999, "Director", 1);

        movie.setBookedBy(user);

        assertEquals(movie.getBookedBy(), user);
    }
}
