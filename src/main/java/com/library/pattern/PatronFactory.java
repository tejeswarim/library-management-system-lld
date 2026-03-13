package com.library.pattern;

import com.library.model.Patron;
import com.library.model.PremiumPatron;
import com.library.model.RegularPatron;

public class PatronFactory {
    public enum PatronType {
        REGULAR, PREMIUM
    }

    public static Patron createPatron(PatronType type, String patronId, String name, String email) {
        switch (type) {
            case PREMIUM:
                return new PremiumPatron(patronId, name, email);
            case REGULAR:
            default:
                return new RegularPatron(patronId, name, email);
        }
    }
}
