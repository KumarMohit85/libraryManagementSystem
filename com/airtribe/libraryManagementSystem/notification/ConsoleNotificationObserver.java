package com.airtribe.libraryManagementSystem.notification;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Patron;

public class ConsoleNotificationObserver implements Observer {

    @Override
    public void update(Book book, Patron patron) {
        System.out.println("[Console Notification] Hey " + patron.getName() + ", the book " + book.getTitle()
                + " is now available for you!");
    }
}