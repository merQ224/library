package src;
import java.util.logging.Logger;

public class Main {
    // static: shared by the class
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    // main() needs to run before any objects exist
    public static void main(String[] args) {
        Book book = new Book(
            "Hunger Games", 
            "Suzanne Collins", 
            "Fiction", 
            true
        );

        Library library = new Library();

        // Add book to library
        library.addBook(book);

        // Find book by title

        // Borrow book

        // Total books in library
        int totalBooks = library.totalBooks();

        logger.info("Total books in library: " + totalBooks);
    }
}
