package com.airtribe.libraryManagementSystem.notification;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Patron;

public interface Observer {
    void update(Book book, Patron patron);
}
