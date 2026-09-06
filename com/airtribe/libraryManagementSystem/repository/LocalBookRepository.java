package com.airtribe.libraryManagementSystem.repository;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.search.SearchBook;

public class LocalBookRepository implements BookRepository {
    private List<Book> books;

    public LocalBookRepository() {
        books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void removeBook(int id) {
        books.remove(getBookById(id));
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public List<Book> searchBook(SearchBook strategy, String query) {
        return strategy.searchBook(books, query);
    }
}
