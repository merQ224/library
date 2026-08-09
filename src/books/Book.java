package src.books;

import src.constants.BookCondition;
import src.constants.BookStatus;

public class Book {
    private String title;
    private String author;
    private String genre;
    private BookStatus status;
    private BookCondition condition;

    public Book(String title, String author, String genre) {
        this(title, author, genre, BookCondition.NEW);
    }

    public Book(String title, String author, String genre, BookCondition condition) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = BookStatus.AVAILABLE;
        this.condition = condition;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public BookStatus getStatus() {
        return status;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
