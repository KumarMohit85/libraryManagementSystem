package com.airtribe.libraryManagementSystem.repository;

import java.util.List;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.search.SearchBook;

public interface BookRepository {
    public void addBook(Book book);

    public List<Book> getAllBooks();

    public Book getBookById(int id);

    public void removeBook(int id);

    public void updateBook(Book book);

    public List<Book> searchBook(SearchBook strategy, String query);
}
