package com.airtribe.libraryManagementSystem;

import java.util.List;
import java.util.Scanner;

import com.airtribe.libraryManagementSystem.constants.AppConstants;
import com.airtribe.libraryManagementSystem.exception.BookNotFoundException;
import com.airtribe.libraryManagementSystem.exception.BookingNotFoundException;
import com.airtribe.libraryManagementSystem.exception.InvalidInputException;
import com.airtribe.libraryManagementSystem.exception.PatronNotFoundException;
import com.airtribe.libraryManagementSystem.logging.Logger;
import com.airtribe.libraryManagementSystem.logging.LoggerFactory;
import com.airtribe.libraryManagementSystem.model.Book;
import com.airtribe.libraryManagementSystem.model.Booking;
import com.airtribe.libraryManagementSystem.model.Patron;
import com.airtribe.libraryManagementSystem.notification.ConsoleNotificationObserver;
import com.airtribe.libraryManagementSystem.notification.EmailNotificationObserver;
import com.airtribe.libraryManagementSystem.notification.Subject;
import com.airtribe.libraryManagementSystem.notification.WaitlistNotificationService;
import com.airtribe.libraryManagementSystem.repository.LocalBookRepository;
import com.airtribe.libraryManagementSystem.repository.LocalBookingRepository;
import com.airtribe.libraryManagementSystem.repository.LocalPatronRepository;
import com.airtribe.libraryManagementSystem.search.SearchBookByAuthor;
import com.airtribe.libraryManagementSystem.search.SearchBookByISBN;
import com.airtribe.libraryManagementSystem.search.SearchBookById;
import com.airtribe.libraryManagementSystem.search.SearchBookByTitle;
import com.airtribe.libraryManagementSystem.services.LibraryService;
import com.airtribe.libraryManagementSystem.services.LibraryServiceImpl;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Library Management System started.");

        Subject notificationService = new WaitlistNotificationService();
        notificationService.addObserver(new ConsoleNotificationObserver());
        notificationService.addObserver(new EmailNotificationObserver());

        LibraryService libraryService = new LibraryServiceImpl(
                new LocalBookRepository(),
                new LocalPatronRepository(),
                new LocalBookingRepository(),
                notificationService);

        while (true) {
            System.out.print(AppConstants.MAIN_MENU);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case AppConstants.ADD_BOOK:
                    try {
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Enter publication year: ");
                        String year = scanner.nextLine();
                        libraryService.addBook(new Book(title, author, genre, isbn, year));
                        System.out.println("Book added successfully.");
                    } catch (InvalidInputException e) {
                        logger.error("Add book failed: " + e.getMessage());
                    }
                    break;

                case AppConstants.VIEW_ALL_BOOKS:
                    List<Book> books = libraryService.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books available.");
                    } else {
                        books.forEach(b -> System.out.println(
                                "[" + b.getId() + "] " + b.getTitle() + " by " + b.getAuthor() + " (ISBN: "
                                        + b.getIsbn() + ")"));
                    }
                    break;

                case AppConstants.BORROW_BOOK:
                    try {
                        System.out.print("Enter Book ID: ");
                        int bookId = scanner.nextInt();
                        System.out.print("Enter Patron ID: ");
                        int patronId = scanner.nextInt();
                        libraryService.borrowBook(bookId, patronId);
                    } catch (BookNotFoundException | PatronNotFoundException e) {
                        logger.error("Borrow book failed: " + e.getMessage());
                    }
                    break;

                case AppConstants.RETURN_BOOK:
                    try {
                        System.out.print("Enter Booking ID: ");
                        int bookingId = scanner.nextInt();
                        libraryService.returnBook(bookingId);
                    } catch (BookingNotFoundException e) {
                        logger.error("Return book failed: " + e.getMessage());
                    }
                    break;

                case AppConstants.SEARCH_BOOKS:
                    try {
                        System.out.print(AppConstants.SEARCH_MENU);
                        int searchChoice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter search query: ");
                        String query = scanner.nextLine();

                        List<Book> results = switch (searchChoice) {
                            case AppConstants.SEARCH_BY_TITLE ->
                                libraryService.searchBooks(new SearchBookByTitle(), query);
                            case AppConstants.SEARCH_BY_AUTHOR ->
                                libraryService.searchBooks(new SearchBookByAuthor(), query);
                            case AppConstants.SEARCH_BY_ISBN ->
                                libraryService.searchBooks(new SearchBookByISBN(), query);
                            case AppConstants.SEARCH_BY_ID -> libraryService.searchBooks(new SearchBookById(), query);
                            default -> {
                                System.out.println("Invalid search option.");
                                yield List.of();
                            }
                        };

                        if (results.isEmpty()) {
                            System.out.println("No books found.");
                        } else {
                            results.forEach(b -> System.out.println(
                                    "[" + b.getId() + "] " + b.getTitle() + " by " + b.getAuthor() + " (ISBN: "
                                            + b.getIsbn() + ")"));
                        }
                    } catch (InvalidInputException e) {
                        logger.error("Search failed: " + e.getMessage());
                    }
                    break;

                case AppConstants.ADD_PATRON:
                    try {
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();
                        System.out.print("Enter address: ");
                        String address = scanner.nextLine();
                        libraryService.addPatron(new Patron(name, phone, address));
                        System.out.println("Patron registered successfully.");
                    } catch (InvalidInputException e) {
                        logger.error("Add patron failed: " + e.getMessage());
                    }
                    break;

                case AppConstants.VIEW_ALL_PATRONS:
                    List<Patron> patrons = libraryService.getAllPatrons();
                    if (patrons.isEmpty()) {
                        System.out.println("No patrons registered.");
                    } else {
                        patrons.forEach(p -> System.out.println(
                                "[" + p.getId() + "] " + p.getName() + " | " + p.getPhone()));
                    }
                    break;

                case AppConstants.VIEW_ALL_BOOKINGS:
                    List<Booking> allBookings = libraryService.getAllBookings();
                    if (allBookings.isEmpty()) {
                        System.out.println("No bookings found.");
                    } else {
                        allBookings.forEach(b -> System.out.println(
                                "Booking [" + b.getId() + "] Book ID: " + b.getBookId() +
                                        " | Patron ID: " + b.getPatronId() +
                                        " | Date: " + b.getBookingDate() +
                                        " | Returned: " + b.isReturned()));
                    }
                    break;

                case AppConstants.VIEW_BOOK_HISTORY:
                    try {
                        System.out.print("Enter Book ID: ");
                        int historyBookId = scanner.nextInt();
                        List<Booking> bookHistory = libraryService.getBookingsByBook(historyBookId);
                        if (bookHistory.isEmpty()) {
                            System.out.println("No booking history for this book.");
                        } else {
                            bookHistory.forEach(b -> System.out.println(
                                    "Booking [" + b.getId() + "] Patron ID: " + b.getPatronId() +
                                            " | Date: " + b.getBookingDate() +
                                            " | Returned: " + b.isReturned()));
                        }
                    } catch (BookNotFoundException e) {
                        logger.error("View book history failed: " + e.getMessage());
                    }
                    break;

                case AppConstants.VIEW_USER_HISTORY:
                    try {
                        System.out.print("Enter Patron ID: ");
                        int historyPatronId = scanner.nextInt();
                        Patron historyPatron = libraryService.getPatronById(historyPatronId);
                        List<Booking> userHistory = libraryService.getBookingsByPatron(historyPatronId);
                        if (userHistory.isEmpty()) {
                            System.out.println("No history found for " + historyPatron.getName() + ".");
                        } else {
                            System.out.println("\n--- Booking History for " + historyPatron.getName() + " ---");
                            userHistory.forEach(b -> {
                                Book historyBook = libraryService.getBookById(b.getBookId());
                                System.out.println(
                                        "Booking [" + b.getId() + "] " +
                                                "'" + historyBook.getTitle() + "' by " + historyBook.getAuthor() +
                                                " | Date: " + b.getBookingDate() +
                                                " | Returned: " + b.isReturned());
                            });
                        }
                    } catch (PatronNotFoundException e) {
                        logger.error("View user history failed: " + e.getMessage());
                    }
                    break;

                case AppConstants.EXIT:
                    logger.info("Library Management System shutting down.");
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
