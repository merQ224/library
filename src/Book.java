package src;

import java.util.logging.Logger;

public class Book {
    private static final Logger logger = Logger.getLogger(Book.class.getName());

    private String title;
    private String author;
    private String genre;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String genre, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public String getTitle() {
        return title;
    }

    public String author() {
        return author;
    }

    public String genre() {
        return genre;
    }

    public String isbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
