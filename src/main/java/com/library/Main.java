package com.library;

import com.library.exception.*;
import com.library.model.*;
import com.library.pattern.*;
import com.library.service.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Library Management System");

        LibraryManager manager = LibraryManager.getInstance();
        BookService bookService = manager.getBookService();
        PatronService patronService = manager.getPatronService();
        LendingService lendingService = manager.getLendingService();

        try {
            // Add books
            Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2018);
            Book book2 = new Book("978-0596009205", "Head First Design Patterns", "Eric Freeman", 2004);
            bookService.addBook(book1);
            bookService.addBook(book2);

            // Create patrons using Factory pattern
            Patron patron1 = PatronFactory.createPatron(PatronFactory.PatronType.REGULAR, "P001", "John Doe", "john@example.com");
            Patron patron2 = PatronFactory.createPatron(PatronFactory.PatronType.PREMIUM, "P002", "Jane Smith", "jane@example.com");
            patronService.addPatron(patron1);
            patronService.addPatron(patron2);

            // Search books using Strategy pattern
            System.out.println("\nSearching by title:");
            bookService.searchBooks(new TitleSearchStrategy(), "Effective").forEach(book -> System.out.println(book));

            System.out.println("\nSearching by author:");
            bookService.searchBooks(new AuthorSearchStrategy(), "Bloch").forEach(book -> System.out.println(book));

            // Checkout book
            lendingService.checkoutBook("978-0134685991", "P001");
            System.out.println("Book checked out successfully");

            // Setup observer for reservation
            PatronObserver observer = new PatronObserver(patron2);
            lendingService.reserveBook("978-0134685991", observer);
            lendingService.attach(observer);

            // Return book (triggers notification)
            lendingService.returnBook("978-0134685991", "P001");
            System.out.println("Book returned successfully");

        } catch (BookNotFoundException | PatronNotFoundException | BookNotAvailableException | BorrowLimitExceededException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nLibrary Management System demo completed");
    }
}
