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
            "978-0-439-02352-8", 
            true
        );

        logger.info("Title: " + book.getTitle());
    }
}
