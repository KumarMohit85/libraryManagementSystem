package com.airtribe.libraryManagementSystem.util;

public class IdGenerator {
    private static int bookCounter = 0;
    private static int patronCounter = 0;
    private static int bookingCounter = 0;

    public static int getNextBookId() {
        return ++bookCounter;
    }

    public static int getNextPatronId() {
        return ++patronCounter;
    }

    public static int getNextBookingId() {
        return ++bookingCounter;
    }

}
