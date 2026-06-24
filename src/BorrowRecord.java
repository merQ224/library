package src;

import java.time.LocalDate;

public class BorrowRecord {
    private final Book book;
    private final User borrower;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    public BorrowRecord(Book book, User borrower, LocalDate borrowDate, int loanDays) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(loanDays);
    }

    public Book getBook() { return book; }
    public User getBorrower() { return borrower; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public boolean isActive() { return returnDate == null; }

    public boolean isOverdue(LocalDate asOf) { return asOf.isAfter(dueDate); }

    public long daysOverdue(LocalDate asOf) {
        return Math.max(0, asOf.toEpochDay() - dueDate.toEpochDay());
    }

    public void close(LocalDate date) { this.returnDate = date; }
}
