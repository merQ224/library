package src;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Library {
    private static final Logger logger = Logger.getLogger(Library.class.getName());
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        logger.info("Successfully added: " + book.getTitle());
    }

    public void borrowBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && book.getIsAvailable()) {
            book.setAvailable(false);
            logger.info("You have borrowed: " + book.getTitle());
        } else {
            logger.warning("Book: " + title + " is not available");
        }
    }

    public void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && !book.getIsAvailable()) {
            book.setAvailable(true);
            logger.info("You have returned: " + book.getTitle());
        } else {
            logger.warning("Book: " + title + " was not borrowed");
        }
    }

    public Book findBookByTitle(String title) {
        logger.info("Find book by title: " + title);
        return books.stream()
            .filter(book -> book.getTitle().equals(title))
            .findFirst()
            .orElse(null);
    }

    public int getTotalBooks() {
        return books.size();
    }

    public void addVisitor(User user) {
        users.add(user);
        user.setIsMember(false);
        logger.info("Recorded visitor entrance: " + user.getName());
    }

    public void addMember(User user) {
        users.add(user);
        user.setIsMember(true);
        logger.info("Recorded member entrance: " + user.getName());
    }

    public int getTotalUsers() {
        return users.size();
    }

    public int getTotalMembers() {
        return users.stream()
            .filter(user -> user.getIsMember() == true)
            .collect(Collectors.toList())
            .size();
    }

    public int getTotalVisitors() {
        return users.stream()
            .filter(user -> user.getIsMember() == false)
            .collect(Collectors.toList())
            .size();
    }
}