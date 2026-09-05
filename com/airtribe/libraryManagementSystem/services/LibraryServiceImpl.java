package com.airtribe.libraryManagementSystem.services;

import java.util.Date;
import java.util.List;

import com.airtribe.libraryManagementSystem.logging.Logger;
import com.airtribe.libraryManagementSystem.logging.LoggerFactory;

import com.airtribe.libraryManagementSystem.exception.BookNotFoundException;
import com.airtribe.libraryManagementSystem.exception.BookingNotFoundException;
import com.airtribe.libraryManagementSystem.exception.InvalidInputException;
import com.airtribe.libraryManagementSystem.exception.PatronNotFoundException;
import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Booking;
import com.airtribe.libraryManagementSystem.model.Patron;
import com.airtribe.libraryManagementSystem.notification.Subject;
import com.airtribe.libraryManagementSystem.repository.BookRepository;
import com.airtribe.libraryManagementSystem.repository.BookingRepository;
import com.airtribe.libraryManagementSystem.repository.PatronRepository;
import com.airtribe.libraryManagementSystem.search.SearchBook;

public class LibraryServiceImpl implements LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;
    private final BookingRepository bookingRepository;
    private final Subject notificationService;

    public LibraryServiceImpl(BookRepository bookRepository, PatronRepository patronRepository,
            BookingRepository bookingRepository, Subject notificationService) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.bookingRepository = bookingRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void addBook(Book book) {
        if (book == null || book.getTitle() == null || book.getTitle().isBlank()) {
            throw new InvalidInputException("Book title cannot be empty.");
        }
        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
            throw new InvalidInputException("Book author cannot be empty.");
        }
        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            throw new InvalidInputException("Book ISBN cannot be empty.");
        }
        bookRepository.addBook(book);
        logger.info("Book added: '" + book.getTitle() + "' by " + book.getAuthor());
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public void addPatron(Patron patron) {
        if (patron == null || patron.getName() == null || patron.getName().isBlank()) {
            throw new InvalidInputException("Patron name cannot be empty.");
        }
        if (patron.getPhone() == null || patron.getPhone().isBlank()) {
            throw new InvalidInputException("Patron phone cannot be empty.");
        }
        patronRepository.addPatron(patron);
        logger.info("Patron registered: " + patron.getName());
    }

    @Override
    public List<Patron> getAllPatrons() {
        return patronRepository.getAllPatrons();
    }

    @Override
    public void borrowBook(int bookId, int patronId) {
        Book book = bookRepository.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }
        Patron patron = patronRepository.getPatronById(patronId);
        if (patron == null) {
            throw new PatronNotFoundException("Patron not found with ID: " + patronId);
        }

        if (bookingRepository.isBookBooked(bookId)) {
            bookingRepository.addToWaitlist(bookId, patronId);
            logger.warning("Book '" + book.getTitle() + "' already booked. Patron '" + patron.getName() + "' added to waitlist.");
            System.out.println("Book is already booked. You will be notified when it becomes available.");
            return;
        }

        Booking booking = new Booking(bookId, patronId, new Date());
        bookingRepository.addBooking(booking);
        logger.info("Book '" + book.getTitle() + "' borrowed by " + patron.getName());
        System.out.println("Book '" + book.getTitle() + "' has been borrowed by " + patron.getName() + ".");
    }

    @Override
    public void returnBook(int bookingId) {
        Booking booking = bookingRepository.getBookingById(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }

        bookingRepository.returnBooking(bookingId, new Date());
        logger.info("Booking ID " + bookingId + " returned successfully.");
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
        if (query == null || query.isBlank()) {
            throw new InvalidInputException("Search query cannot be empty.");
        }
        return bookRepository.searchBook(strategy, query);
    }

    @Override
    public List<Booking> getBookingsByPatron(int patronId) {
        Patron patron = patronRepository.getPatronById(patronId);
        if (patron == null) {
            throw new PatronNotFoundException("Patron not found with ID: " + patronId);
        }
        return bookingRepository.getBookingHistoryByPatron(patronId);
    }

    @Override
    public List<Booking> getBookingsByBook(int bookId) {
        Book book = bookRepository.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }
        return bookingRepository.getBookingHistoryByBook(bookId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    @Override
    public Book getBookById(int bookId) {
        Book book = bookRepository.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }
        return book;
    }

    @Override
    public Patron getPatronById(int patronId) {
        Patron patron = patronRepository.getPatronById(patronId);
        if (patron == null) {
            throw new PatronNotFoundException("Patron not found with ID: " + patronId);
        }
        return patron;
    }
}
