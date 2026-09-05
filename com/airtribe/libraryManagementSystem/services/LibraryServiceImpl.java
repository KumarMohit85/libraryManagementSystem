package com.airtribe.libraryManagementSystem.services;

import java.util.Date;
import java.util.List;

import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Booking;
import com.airtribe.libraryManagementSystem.model.Patron;
import com.airtribe.libraryManagementSystem.notification.ConsoleNotificationObserver;
import com.airtribe.libraryManagementSystem.notification.EmailNotificationObserver;
import com.airtribe.libraryManagementSystem.notification.WaitlistNotificationService;
import com.airtribe.libraryManagementSystem.repository.BookRepository;
import com.airtribe.libraryManagementSystem.repository.BookingRepository;
import com.airtribe.libraryManagementSystem.repository.PatronRepository;
import com.airtribe.libraryManagementSystem.search.SearchBook;

public class LibraryServiceImpl implements LibraryService {

    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;
    private final BookingRepository bookingRepository;
    private final WaitlistNotificationService notificationService;

    public LibraryServiceImpl(BookRepository bookRepository, PatronRepository patronRepository,
            BookingRepository bookingRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.bookingRepository = bookingRepository;

        this.notificationService = new WaitlistNotificationService();
        this.notificationService.addObserver(new ConsoleNotificationObserver());
        this.notificationService.addObserver(new EmailNotificationObserver());
    }

    @Override
    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public void addPatron(Patron patron) {
        patronRepository.addPatron(patron);
    }

    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.getAllPatrons();
    }

    @Override
    public void borrowBook(int bookId, int patronId) {
        if (bookingRepository.isBookBooked(bookId)) {
            bookingRepository.addToWaitlist(bookId, patronId);
            System.out.println("Book is already booked. You will be notified when it becomes available.");
            return;
        }

        Patron patron = patronRepository.getPatronById(patronId);
        Book book = bookRepository.getBookById(bookId);
        Booking booking = new Booking(bookId, patronId, new Date());
        bookingRepository.addBooking(booking);
        System.out.println("Book '" + book.getTitle() + "' has been borrowed by " + patron.getName() + ".");
    }

    @Override
    public void returnBook(int bookingId) {
        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("No booking found with ID: " + bookingId);
            return;
        }

        bookingRepository.returnBooking(bookingId, new Date());
        System.out.println("Book returned successfully.");

        int nextPatronId = bookingRepository.getNextInWaitlist(booking.getBookId());
        if (nextPatronId != -1) {
            Book book = bookRepository.getBookById(booking.getBookId());
            Patron patron = patronRepository.getPatronById(nextPatronId);
            notificationService.broadcast(book, patron);
        }
    }

    @Override
    public List<Book> searchBooks(SearchBook strategy, String query) {
        return bookRepository.searchBook(strategy, query);
    }

    @Override
    public List<Booking> getBookingsByPatron(int patronId) {
        return bookingRepository.getBookingHistoryByPatron(patronId);
    }

    @Override
    public List<Booking> getBookingsByBook(int bookId) {
        return bookingRepository.getBookingHistoryByBook(bookId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    @Override
    public Book getBookById(int bookId) {
        return bookRepository.getBookById(bookId);
    }

    @Override
    public Patron getPatronById(int patronId) {
        return patronRepository.getPatronById(patronId);
    }
}
