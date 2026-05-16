package src;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Library {
    private static final Logger logger = Logger.getLogger(Library.class.getName());
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        logger.info("Initiate add book: " + book.getTitle());
        books.add(book);
        logger.info("Successfully added");
    }

    public Book findBookByTitle(String title) {
        logger.info("Find book by title: " + title);
        return books.stream()
            .filter(book -> book.getTitle().equals(title))
            .findFirst()
            .orElse(null);
    }

    public int totalBooks() {
        return books.size();
    }

    public void borrowBook(String title) {
        logger.info("Initiate borrow book");
        Book book = findBookByTitle(title);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            logger.info("You have borrowed: " + book.getTitle());
        } else {
            logger.warning("Book" + title + " is not available");
        }
    }
}