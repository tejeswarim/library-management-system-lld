package com.library.service;

import com.library.exception.*;
import com.library.model.*;
import com.library.pattern.Subject;
import com.library.pattern.Observer;

import java.time.LocalDate;
import java.util.*;

public class LendingService implements Subject {
    private final BookService bookService;
    private final PatronService patronService;
    private final Map<String, List<Observer>> reservations;
    private final List<Observer> observers;

    public LendingService(BookService bookService, PatronService patronService) {
        this.bookService = bookService;
        this.patronService = patronService;
        this.reservations = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public void checkoutBook(String isbn, String patronId) throws BookNotFoundException, PatronNotFoundException, 
            BookNotAvailableException, BorrowLimitExceededException {
        Book book = bookService.getBook(isbn);
        Patron patron = patronService.getPatron(patronId);

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new BookNotAvailableException("Book is not available for checkout");
        }

        if (patron.getCurrentBorrowedCount() >= patron.getMaxBooksAllowed()) {
            throw new BorrowLimitExceededException("Patron has reached maximum borrowing limit");
        }

        book.setStatus(BookStatus.CHECKED_OUT);
        LendingRecord record = new LendingRecord(isbn, patronId, LocalDate.now());
        patron.addBorrowingRecord(record);

        System.out.println("Book checked out: " + isbn + " by patron: " + patronId);
    }

    public void returnBook(String isbn, String patronId) throws BookNotFoundException, PatronNotFoundException {
        Book book = bookService.getBook(isbn);
        Patron patron = patronService.getPatron(patronId);

        LendingRecord record = patron.getBorrowingHistory().stream()
                .filter(r -> r.getIsbn().equals(isbn) && r.getReturnDate() == null)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("No active lending record found"));

        record.setReturnDate(LocalDate.now());
        book.setStatus(BookStatus.AVAILABLE);

        System.out.println("Book returned: " + isbn + " by patron: " + patronId);

        if (reservations.containsKey(isbn)) {
            notifyObservers("Book " + book.getTitle() + " is now available");
        }
    }

    public void reserveBook(String isbn, Observer observer) throws BookNotFoundException {
        Book book = bookService.getBook(isbn);
        reservations.computeIfAbsent(isbn, k -> new ArrayList<>()).add(observer);
        System.out.println("Book reserved: " + isbn);
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
