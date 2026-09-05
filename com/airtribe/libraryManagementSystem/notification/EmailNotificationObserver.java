package com.airtribe.libraryManagementSystem.notification;

import com.airtribe.libraryManagementSystem.logging.Logger;
import com.airtribe.libraryManagementSystem.logging.LoggerFactory;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Patron;

public class EmailNotificationObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationObserver.class);

    @Override
    public void update(Book book, Patron patron) {
        String message = "[Email Notification] Sending email to " + patron.getName() + " for book '" + book.getTitle() + "'.";
        logger.info(message);
    }
}
