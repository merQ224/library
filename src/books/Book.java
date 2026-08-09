package src.books;

import src.constants.BookStatus;

public class Book {
    private String title;
    private String author;
    private String genre;
    private BookStatus status;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = BookStatus.AVAILABLE;
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

    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
