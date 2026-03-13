package com.library.service;

import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.pattern.SearchStrategy;

import java.util.*;

public class BookService {
    private final Map<String, Book> books;

    public BookService() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
        System.out.println("Book added: " + book);
    }

    public void removeBook(String isbn) throws BookNotFoundException {
        if (!books.containsKey(isbn)) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
        books.remove(isbn);
        System.out.println("Book removed: " + isbn);
    }

    public void updateBook(Book book) throws BookNotFoundException {
        if (!books.containsKey(book.getIsbn())) {
            throw new BookNotFoundException("Book with ISBN " + book.getIsbn() + " not found");
        }
        books.put(book.getIsbn(), book);
        System.out.println("Book updated: " + book);
    }

    public Book getBook(String isbn) throws BookNotFoundException {
        Book book = books.get(isbn);
        if (book == null) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
        return book;
    }

    public List<Book> searchBooks(SearchStrategy strategy, String query) {
        return strategy.search(new ArrayList<>(books.values()), query);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
}
