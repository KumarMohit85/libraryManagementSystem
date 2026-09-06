package com.airtribe.libraryManagementSystem.util;

public class IdGenerator {

    private static final IdGenerator instance = new IdGenerator();

    private int bookCounter = 0;
    private int patronCounter = 0;
    private int bookingCounter = 0;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return instance;
    }

    public synchronized int getNextBookId() {
        return ++bookCounter;
    }

    public synchronized int getNextPatronId() {
        return ++patronCounter;
    }

    public synchronized int getNextBookingId() {
        return ++bookingCounter;
    }
}
