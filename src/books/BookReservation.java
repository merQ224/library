package src.books;

import src.users.User;

public class BookReservation {
    private Book book;
    private User user;
    private String reservationDate;

    public BookReservation(Book book, User user, String reservationDate) {
        this.book = book;
        this.user = user;
        this.reservationDate = reservationDate;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public String getReservationDate() {
        return reservationDate;
    }
}
