package com.twu.biblioteca.models;

import com.twu.biblioteca.interfaces.IItem;

public class Book implements IItem {
    private int id;
    private String title;
    private String author;
    private int publicationYear;
    private String status;
    private User bookedBy;

    public Book(int id, String title, String author, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.status = "available";
    }

    public String getTitle()
    {
       return this.title;
    }

    public String getAuthor()
    {
        return this.author;
    }

    public int getPublicationYear()
    {
        return  this.publicationYear;
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
        String data = String.format("Id: %s | Title: %s | Author: %s | Publication Year: %s | Status: %s",
                this.id,
                this.title,
                this.author,
                this.publicationYear,
                this.status
        );

        return data + this.additionalData();
    }

    public void setBookedBy(User user) {
        this.bookedBy = user;
    }

    public User getBookedBy() {
        return this.bookedBy;
    }

    public boolean alreadyBooked() {
        return this.status == "booked";
    }

    private String additionalData() {
        User currentUser = CurrentUser.get();

        if (currentUser.isLibrarian() && this.alreadyBooked())
            return String.format(" | Booked by: %s\n", this.bookedBy.libraryId);
        return "\n";
    }
}
