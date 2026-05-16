import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
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
