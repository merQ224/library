package src;

import src.constants.BookStatus;
import src.repository.BookRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final BookRepository bookRepository;
    private final FineService fineService;
    private final List<BookReservation> bookReservations = new ArrayList<>();

    public Library(BookRepository bookRepository, FineService fineService) {
        this.bookRepository = bookRepository;
        this.fineService = fineService;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
        System.out.println("Successfully added: " + book.getTitle());
    }

    public void borrowBook(String title, User borrower, LocalDate borrowDate) {
        if (fineService.isRevoked(borrower.getEmail())) {
            System.out.println("Cannot borrow: membership for " + borrower.getEmail() + " has been revoked due to unpaid fines.");
            return;
        }
        Book book = findBookByTitle(title);
        if (book != null && book.isAvailable()) {
            book.setStatus(BookStatus.BORROWED);
            fineService.createBorrowRecord(book, borrower, borrowDate);
            System.out.println("You have borrowed: " + book.getTitle());
        } else {
            System.out.println("Book \"" + title + "\" is not available!");
        }
    }

    public void returnBook(String title, LocalDate returnDate) {
        Book book = findBookByTitle(title);
        if (book == null || book.getStatus() != BookStatus.BORROWED) {
            System.out.println("Book \"" + title + "\" was not borrowed!");
            return;
        }
        BorrowRecord record = fineService.findActiveBorrowByBookTitle(title);
        if (record != null) {
            fineService.processReturn(record, returnDate);
        }
        book.setStatus(BookStatus.AVAILABLE);
        System.out.println("You have returned: " + book.getTitle());
    }

    public void reserveBook(String title, User user, String date) {
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book \"" + title + "\" not found.");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Book \"" + title + "\" is not available to reserve.");
            return;
        }
        book.setStatus(BookStatus.RESERVED);
        bookReservations.add(new BookReservation(book, user, date));
        System.out.println("Book \"" + book.getTitle() + "\" reserved for " + user.getName() + " on " + date + ".");
    }

    public void cancelBookReservation(String title) {
        BookReservation reservation = bookReservations.stream()
            .filter(r -> r.getBook().getTitle().equals(title))
            .findFirst()
            .orElse(null);

        if (reservation == null) {
            System.out.println("No reservation found for \"" + title + "\".");
            return;
        }
        reservation.getBook().setStatus(BookStatus.AVAILABLE);
        bookReservations.remove(reservation);
        System.out.println("Reservation for \"" + title + "\" has been cancelled.");
    }

    public void markAsLost(String title, LocalDate date) {
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book \"" + title + "\" not found.");
            return;
        }
        if (book.getStatus() == BookStatus.LOST) {
            System.out.println("Book \"" + title + "\" is already marked as lost.");
            return;
        }
        BorrowRecord record = fineService.findActiveBorrowByBookTitle(title);
        if (record != null) {
            fineService.issueLostBookFine(record, date);
        }
        book.setStatus(BookStatus.LOST);
        System.out.println("Book \"" + book.getTitle() + "\" has been marked as lost.");
    }

    public List<Book> getListOfBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAll().stream()
            .filter(Book::isAvailable)
            .collect(Collectors.toList());
    }

    public List<BookReservation> getBookReservations() {
        return bookReservations;
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(null);
    }

    public int getTotalNumberOfBooks() {
        return bookRepository.findAll().size();
    }
}
