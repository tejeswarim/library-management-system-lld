package com.library.model;

import java.time.LocalDate;

public class LendingRecord {
    private final String isbn;
    private final String patronId;
    private final LocalDate checkoutDate;
    private LocalDate returnDate;

    public LendingRecord(String isbn, String patronId, LocalDate checkoutDate) {
        this.isbn = isbn;
        this.patronId = patronId;
        this.checkoutDate = checkoutDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPatronId() {
        return patronId;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "LendingRecord{" +
                "isbn='" + isbn + '\'' +
                ", patronId='" + patronId + '\'' +
                ", checkoutDate=" + checkoutDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
