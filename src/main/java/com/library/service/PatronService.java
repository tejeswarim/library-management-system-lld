package com.library.service;

import com.library.exception.PatronNotFoundException;
import com.library.model.Patron;

import java.util.*;

public class PatronService {
    private final Map<String, Patron> patrons;

    public PatronService() {
        this.patrons = new HashMap<>();
    }

    public void addPatron(Patron patron) {
        patrons.put(patron.getPatronId(), patron);
        System.out.println("Patron added: " + patron);
    }

    public void updatePatron(Patron patron) throws PatronNotFoundException {
        if (!patrons.containsKey(patron.getPatronId())) {
            throw new PatronNotFoundException("Patron with ID " + patron.getPatronId() + " not found");
        }
        patrons.put(patron.getPatronId(), patron);
        System.out.println("Patron updated: " + patron);
    }

    public Patron getPatron(String patronId) throws PatronNotFoundException {
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            throw new PatronNotFoundException("Patron with ID " + patronId + " not found");
        }
        return patron;
    }

    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patrons.values());
    }
}
