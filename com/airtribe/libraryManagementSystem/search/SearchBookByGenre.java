package com.airtribe.libraryManagementSystem.search;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.libraryManagementSystem.model.Book;

public class SearchBookByGenre implements SearchBook {
    @Override
    public List<Book> searchBook(List<Book> books, String query) {
        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getGenre().toLowerCase().contains(query.toLowerCase())) {
                result.add(book);
            }
        }
        return result;

    }
}
