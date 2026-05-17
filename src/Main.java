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

        // Create Users
        User user1 = new User("Lebron", "James", "test1@gmail.com");
        User user2 = new User("Michael", "Jordan", "test2@gmail.com");
        User user3 = new User("Steph", "Curry", "test3@gmail.com");
        User user4 = new User("John", "Doe", "test4@yahoo.co.nz");
        User user5 = new User("Michael", "Phelps", "test5@gmail.com");

        Library library = new Library();

        // Add book to library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        // Add users to library
        library.addMember(user1);
        library.addVisitor(user2);
        library.addMember(user3);
        library.addVisitor(user4);
        library.addMember(user5);

        // Find book by title
        Book foundBook = library.findBookByTitle("1984");
        if (foundBook != null) {
            logger.info("Found book: " + foundBook.getTitle() + " by " + foundBook.getAuthor());
        } else {
            logger.warning("Book not found");
        }

        // Borrow book
        library.borrowBook("The Hobbit");


        // Total books in library
        int totalBooks = library.getTotalBooks();

        logger.info("Total books in library: " + totalBooks);
        logger.info("Is 'The Hobbit' available? " + "[" + book2.getIsAvailable() + "]");
        
        // Return book
        logger.info("Returning The Hobbit book");
        library.returnBook("The Hobbit");
        logger.info("Is 'The Hobbit' available? " + "[" + book2.getIsAvailable() + "]");

        // Users
        logger.info("Is Lebron a member? " + "[" + user1.getIsMember() + "]");
        logger.info("Is Michael Jordan a visitor? " + "[" + user2.getIsMember() + "]");
        logger.info("Total users: " + library.getTotalBooks());
        logger.info("Total visitors: " + library.getTotalVisitors());
        logger.info("Total members: " + library.getTotalMembers());
    }
}
