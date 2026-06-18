package src;

import src.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final BookRepository bookRepository;

    public Library(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
        System.out.println("Successfully added: " + book.getTitle());
    }

    public void borrowBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && book.getIsAvailable()) {
            book.setAvailable(false);
            System.out.println("You have borrowed: " + book.getTitle());
        } else {
            System.out.println("Book: " + title + " is not available!");
        }
    }

    public void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && !book.getIsAvailable()) {
            book.setAvailable(true);
            System.out.println("You have returned: " + book.getTitle());
        } else {
            System.out.println("Book: " + title + " was not borrowed!");
        }
    }

    public List<Book> getListOfBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAll().stream()
            .filter(Book::getIsAvailable)
            .collect(Collectors.toList());
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(null);
    }

    public int getTotalNumberOfBooks() {
        return bookRepository.findAll().size();
    }
}
