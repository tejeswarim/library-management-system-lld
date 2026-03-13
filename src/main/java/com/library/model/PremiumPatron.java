package com.library.model;

public class PremiumPatron extends Patron {
    private static final int MAX_BOOKS = 10;

    public PremiumPatron(String patronId, String name, String email) {
        super(patronId, name, email, MAX_BOOKS);
    }
}
