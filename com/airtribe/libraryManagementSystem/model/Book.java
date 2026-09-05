package com.airtribe.libraryManagementSystem.model;

import com.airtribe.libraryManagementSystem.util.IdGenerator;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private String isbn;
    private String publicationYear;

    public Book(String title, String author, String genre, String isbn, String publicationYear) {
        this.id = IdGenerator.getNextBookId();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

}
