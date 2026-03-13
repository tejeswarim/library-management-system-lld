package com.library.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Patron {
    private final String patronId;
    private String name;
    private String email;
    private final List<LendingRecord> borrowingHistory;
    private final int maxBooksAllowed;

    public Patron(String patronId, String name, String email, int maxBooksAllowed) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
        this.maxBooksAllowed = maxBooksAllowed;
        this.borrowingHistory = new ArrayList<>();
    }

    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LendingRecord> getBorrowingHistory() {
        return new ArrayList<>(borrowingHistory);
    }

    public void addBorrowingRecord(LendingRecord record) {
        borrowingHistory.add(record);
    }

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }

    public long getCurrentBorrowedCount() {
        return borrowingHistory.stream()
                .filter(record -> record.getReturnDate() == null)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return Objects.equals(patronId, patron.patronId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patronId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "patronId='" + patronId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
