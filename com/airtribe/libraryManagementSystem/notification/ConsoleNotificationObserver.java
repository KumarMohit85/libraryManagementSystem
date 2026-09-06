package com.airtribe.libraryManagementSystem.notification;

import com.airtribe.libraryManagementSystem.logging.Logger;
import com.airtribe.libraryManagementSystem.logging.LoggerFactory;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Patron;

public class ConsoleNotificationObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleNotificationObserver.class);

    @Override
    public void update(Book book, Patron patron) {
        String message = "Hey " + patron.getName() + ", the book '" + book.getTitle() + "' is now available for you!";
        logger.info("[Console Notification] " + message);
    }
}