package com.library;

import com.library.service.BookService;
import com.library.service.LendingService;
import com.library.service.PatronService;

public class LibraryManager {
    private static LibraryManager instance;
    private final BookService bookService;
    private final PatronService patronService;
    private final LendingService lendingService;

    private LibraryManager() {
        this.bookService = new BookService();
        this.patronService = new PatronService();
        this.lendingService = new LendingService(bookService, patronService);
    }

    public static synchronized LibraryManager getInstance() {
        if (instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }

    public BookService getBookService() {
        return bookService;
    }

    public PatronService getPatronService() {
        return patronService;
    }

    public LendingService getLendingService() {
        return lendingService;
    }
}
