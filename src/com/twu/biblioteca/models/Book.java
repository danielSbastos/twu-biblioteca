package com.twu.biblioteca.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private int publicationYear;
    private String status;

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
}
