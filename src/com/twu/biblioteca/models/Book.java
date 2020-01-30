package com.twu.biblioteca.models;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String status;

    public Book(String title, String author, int publicationYear) {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
