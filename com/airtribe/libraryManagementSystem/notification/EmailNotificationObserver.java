package com.airtribe.libraryManagementSystem.notification;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Patron;

public class EmailNotificationObserver implements Observer {

    @Override
    public void update(Book book, Patron patron) {
        System.out.println(
                "[Email Notification] Sending email to " + patron.getName() + " for book " + book.getTitle() + ".");
    }
}
