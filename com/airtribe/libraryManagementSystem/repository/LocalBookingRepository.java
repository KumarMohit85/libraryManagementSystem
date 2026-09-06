package com.airtribe.libraryManagementSystem.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.airtribe.libraryManagementSystem.model.Booking;

public class LocalBookingRepository implements BookingRepository {
    private List<Booking> bookings;
    private HashMap<Integer, List<Booking>> bookingsByBookId;
    private HashMap<Integer, List<Booking>> bookingsByPatronId;
    private HashMap<Integer, Queue<Integer>> waitlisting; // {bookId:[patronId1,patronId2...]}

    public LocalBookingRepository() {
        bookings = new ArrayList<>();
        bookingsByBookId = new HashMap<>();
        bookingsByPatronId = new HashMap<>();
        waitlisting = new HashMap<>();
    }

    @Override
    public void addBooking(Booking booking) {
        bookings.add(booking);
        bookingsByBookId.putIfAbsent(booking.getBookId(), new ArrayList<>());
        bookingsByPatronId.putIfAbsent(booking.getPatronId(), new ArrayList<>());
        bookingsByBookId.get(booking.getBookId()).add(booking);
        bookingsByPatronId.get(booking.getPatronId()).add(booking);
    }

    @Override
    public List<Booking> getBookingHistoryByBook(int bookId) {
        return bookingsByBookId.getOrDefault(bookId, new ArrayList<Booking>());
    }

    @Override
    public List<Booking> getBookingHistoryByPatron(int patronId) {
        return bookingsByPatronId.getOrDefault(patronId, new ArrayList<Booking>());
    }

    @Override
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    @Override
    public boolean isBookBooked(int bookId) {
        for (Booking booking : bookings) {
            if (booking.getBookId() == bookId && !booking.isReturned()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Booking getBookingById(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public void returnBooking(int id, Date returnDate) {
        Booking booking = getBookingById(id);
        if (booking != null) {
            booking.setReturnDate(returnDate);
            booking.setReturned(true);
        }
    }

    @Override
    public void addToWaitlist(int bookId, int patronId) {
        waitlisting.computeIfAbsent(bookId, k -> new LinkedList<>()).offer(patronId);
    }

    @Override
    public int getNextInWaitlist(int bookId) {
        Queue<Integer> queue = waitlisting.get(bookId);
        if (queue == null || queue.isEmpty()) {
            return -1;
        }
        return queue.poll();
    }

    @Override
    public void updateBooking(Booking booking) {

    }
}
