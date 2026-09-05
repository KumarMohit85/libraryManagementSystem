# Library Management System

A console-based Library Management System built in Java demonstrating core OOP principles and multiple design patterns.

---

## Features

- Add and view books
- Register and view patrons
- Borrow and return books
- Waitlist management with automatic notifications
- Search books by title, author, ISBN, ID, or genre
- View all bookings, book booking history, and user booking history

---

## How to Run

```bash
javac -d bin $(find . -name "*.java")
java -cp bin com.airtribe.libraryManagementSystem.Main
```

---

## Project Structure

```
com.airtribe.libraryManagementSystem
├── Main.java
├── constants/
│   └── AppConstants.java
├── model/
│   ├── Book.java
│   ├── Patron.java
│   └── Booking.java
├── util/
│   └── IdGenerator.java
├── repository/
│   ├── BookRepository.java
│   ├── PatronRepository.java
│   ├── BookingRepository.java
│   ├── LocalBookRepository.java
│   ├── LocalPatronRepository.java
│   └── LocalBookingRepository.java
├── search/
│   ├── SearchBook.java
│   ├── SearchBookByTitle.java
│   ├── SearchBookByAuthor.java
│   ├── SearchBookByISBN.java
│   ├── SearchBookById.java
│   └── SearchBookByGenre.java
├── notification/
│   ├── Observer.java
│   ├── Subject.java
│   ├── WaitlistNotificationService.java
│   ├── ConsoleNotificationObserver.java
│   └── EmailNotificationObserver.java
└── services/
    ├── LibraryService.java
    └── LibraryServiceImpl.java
```

---

## Design Patterns

| Pattern | Where Used |
|---|---|
| **Facade** | `LibraryServiceImpl` — single entry point over all subsystems |
| **Observer** | `WaitlistNotificationService` notifies patrons on book return |
| **Strategy** | `SearchBook` interface with 5 interchangeable search strategies |
| **Repository** | `BookRepository`, `PatronRepository`, `BookingRepository` abstract data access |

---

## Class Diagram

### 1. Model Layer (Domain)

![Model Layer](ClassDiagrams/ModelLayer(Domain).png)

---

### 2. Repository Layer

![Repository Layer](ClassDiagrams/RepositoryLayer.png)

---

### 3. Search — Strategy Pattern

![Search Strategy Pattern](ClassDiagrams/SearchStrategyPattern.png)

---

### 4. Notification — Observer Pattern

![Notification Observer Pattern](ClassDiagrams/Notification-ObserverPattern.png)

---

### 5. Service — Facade Pattern

![Service Facade Pattern](ClassDiagrams/Service-FacadePattern.png)

---

## Menu Options

```
===== Library Management System =====
1.  Add Book
2.  View All Books
3.  Borrow Book
4.  Return Book
5.  Search Books
6.  Add Patron
7.  View All Patrons
8.  View All Bookings
9.  View Book History
10. View User History
0.  Exit
=====================================
```
