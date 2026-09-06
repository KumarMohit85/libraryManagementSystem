package com.airtribe.libraryManagementSystem.search;

import java.util.List;
import com.airtribe.libraryManagementSystem.model.Book;

public interface SearchBook {
   List<Book> searchBook(List<Book> books, String query);
}
