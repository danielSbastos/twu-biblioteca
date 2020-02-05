package com.twu.biblioteca.models;

import com.twu.biblioteca.interfaces.IItem;

public class Movie implements IItem {
    private int id;
    private String name;
    private int year;
    private String director;
    private int rating;
    private String status;
    private User bookedBy;

    public Movie(int id, String name, int year, String director, int rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.status = "available";
    }

    public int getId() {
        return this.id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String stringifyData() {
        String data = String.format("Id: %s | Name: %s | Year: %s | Director: %s | Rating: %s | Status: %s",
                this.id,
                this.name,
                this.year,
                this.director,
                this.rating,
                this.status
        );

        // FIXME: This code is very bad
        if (CurrentUser.get().role == "librarian" && this.status == "booked")
            data += String.format(" | Booked by: %s\n", this.bookedBy.libraryId);
        else
            data += "\n";

        return data;
    }

    public void setBookedBy(User user) {
        this.bookedBy = user;
    }

    public User getBookedBy() {
        return this.bookedBy;
    }
}
