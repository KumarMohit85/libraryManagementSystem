package com.airtribe.libraryManagementSystem.notification;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Patron;

public interface Subject {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void broadcast(Book book, Patron patron);
}
