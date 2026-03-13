package com.library.model;

public class RegularPatron extends Patron {
    private static final int MAX_BOOKS = 5;

    public RegularPatron(String patronId, String name, String email) {
        super(patronId, name, email, MAX_BOOKS);
    }
}
