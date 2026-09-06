package com.airtribe.libraryManagementSystem.repository;

import java.util.Date;
import java.util.List;

import com.airtribe.libraryManagementSystem.model.Booking;

public interface BookingRepository {
    public void addBooking(Booking booking);

    public List<Booking> getBookingHistoryByBook(int bookId);

    public List<Booking> getBookingHistoryByPatron(int patronId);

    public List<Booking> getAllBookings();

    public boolean isBookBooked(int bookId);

    public Booking getBookingById(int id);

    public void returnBooking(int id, Date returnDate);

    public void addToWaitlist(int bookId, int patronId);

    public int getNextInWaitlist(int bookId);

    public void updateBooking(Booking booking);
}
