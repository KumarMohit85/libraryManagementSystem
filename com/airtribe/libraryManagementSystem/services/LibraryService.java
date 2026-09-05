package com.airtribe.libraryManagementSystem.services;

import java.util.List;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Booking;
import com.airtribe.libraryManagementSystem.model.Patron;
import com.airtribe.libraryManagementSystem.search.SearchBook;

public interface LibraryService {
    void addBook(Book book);

    List<Book> getAllBooks();

    void addPatron(Patron patron);

    List<Patron> getAllPatrons();

    void borrowBook(int bookId, int patronId);

    void returnBook(int bookingId);

    List<Book> searchBooks(SearchBook strategy, String query);

    List<Booking> getBookingsByPatron(int patronId);

    List<Booking> getBookingsByBook(int bookId);

    List<Booking> getAllBookings();

    Book getBookById(int bookId);

    Patron getPatronById(int patronId);
}
