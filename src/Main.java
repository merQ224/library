package src;
import java.util.logging.Logger;

public class Main {
    // static: shared by the class
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    // main() needs to run before any objects exist
    public static void main(String[] args) {
        // Create Books
        Book book1 = new Book("Hunger Games", "Suzanne Collins", "Fiction", true);
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkein", "Fantasy", true);
        Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", true);
        Book book4 = new Book("The Pragmatic Programmer", "Andrew Hunt", "Fiction", true);
        Book book5 = new Book("1984", "George", "Fiction", true);

        Library library = new Library();

        // Add book to library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        // Find book by title
        Book foundBook = library.findBookByTitle("1984");
        logger.info("Book exists");

        // Borrow book
        library.borrowBook("The Hobbit");


        // Total books in library
        int totalBooks = library.totalBooks();

        logger.info("Total books in library: " + totalBooks);
    }
}
