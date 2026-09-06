package com.airtribe.libraryManagementSystem.search;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.libraryManagementSystem.logging.Logger;
import com.airtribe.libraryManagementSystem.logging.LoggerFactory;
import com.airtribe.libraryManagementSystem.model.Book;

public class SearchBookById implements SearchBook {

    private static final Logger logger = LoggerFactory.getLogger(SearchBookById.class);

    @Override
    public List<Book> searchBook(List<Book> books, String query) {
        List<Book> result = new ArrayList<>();
        try {
            int id = Integer.parseInt(query.trim());
            for (Book book : books) {
                if (book.getId() == id) {
                    result.add(book);
                    break;
                }
            }
        } catch (NumberFormatException e) {
            logger.warning("Invalid book ID format entered: '" + query + "'");
            System.out.println("Invalid ID. Please enter a number.");
        }
        return result;
    }
}
