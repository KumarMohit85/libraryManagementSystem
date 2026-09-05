package com.airtribe.libraryManagementSystem.notification;

import java.util.ArrayList;
import java.util.List;
import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Patron;

public class WaitlistNotificationService implements Subject {

    private List<Observer> observers;

    public WaitlistNotificationService() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void broadcast(Book book, Patron patron) {
        for (Observer observer : observers) {
            observer.update(book, patron);
        }
    }

}
