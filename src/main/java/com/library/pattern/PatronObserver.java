package com.library.pattern;

import com.library.model.Patron;

public class PatronObserver implements Observer {
    private final Patron patron;

    public PatronObserver(Patron patron) {
        this.patron = patron;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification to " + patron.getName() + ": " + message);
    }

    public Patron getPatron() {
        return patron;
    }
}
