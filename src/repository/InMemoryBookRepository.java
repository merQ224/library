package src.repository;

import src.books.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {
    private final List<Book> books = new ArrayList<>();

    public InMemoryBookRepository() {
        books.add(new Book("Apples", "Peanut", "Comedy"));
        books.add(new Book("Peaches", "Pan", "Romance"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Novel"));
        books.add(new Book("The Art of Money", "Peanut", "Non-fiction"));
        books.add(new Book("The Art Of War", "Sun Tzu", "Non-fiction"));
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return books.stream()
            .filter(book -> book.getTitle().equals(title))
            .findFirst();
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }
}
