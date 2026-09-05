package com.airtribe.libraryManagementSystem.model;

import java.util.Date;

import com.airtribe.libraryManagementSystem.util.IdGenerator;

public class Booking {
    private int id;
    private int bookId;
    private int patronId;
    private Date bookingDate;
    private Date returnDate;
    private boolean isReturned;

    public Booking(int bookId, int patronId, Date bookingDate) {
        this.id = IdGenerator.getNextBookingId();
        this.bookId = bookId;
        this.patronId = patronId;
        this.bookingDate = bookingDate;
        this.returnDate = null;
        this.isReturned = false;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

}
