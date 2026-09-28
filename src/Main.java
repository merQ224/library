package src;

import src.books.Book;
import src.books.BookReservation;
import src.books.Library;
import src.constants.BookCondition;
import src.fines.Fine;
import src.fines.FineService;
import src.repository.*;
import src.rooms.Reservation;
import src.rooms.ReservationService;
import src.rooms.Room;
import src.users.Admin;
import src.users.Member;
import src.users.Staff;
import src.users.User;
import src.users.UserService;
import src.users.Visitor;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FineService fineService = new FineService(
            new InMemoryFineRepository(),
            new InMemoryBorrowRecordRepository()
        );
        ReservationService reservationService = new ReservationService(new InMemoryRoomRepository());
        Library library = new Library(new InMemoryBookRepository(), fineService);
        UserService userService = new UserService();
        boolean running = true;

        while (running) {
            System.out.println("\n=============================");
            System.out.println("   Welcome to the Library    ");
            System.out.println("=============================");
            System.out.println("  1. Browse available rooms");
            System.out.println("  2. View room reservations");
            System.out.println("  3. Reserve a room");
            System.out.println("  4. Cancel room reservation");
            System.out.println("  5. Browse book catalog");
            System.out.println("  6. Donate a book");
            System.out.println("  7. Borrow a book");
            System.out.println("  8. Return a book");
            System.out.println("  9. Reserve a book");
            System.out.println(" 10. Cancel book reservation");
            System.out.println(" 11. Report a book as lost");
            System.out.println(" 12. Register as a member");
            System.out.println(" 13. Sign in as a visitor");
            System.out.println(" 14. Register as staff");
            System.out.println(" 15. Register as admin");
            System.out.println(" 16. View my fines");
            System.out.println(" 17. Pay a fine");
            System.out.println(" 18. Search catalog");
            System.out.println(" 19. Exit");
            System.out.println("-----------------------------");
            System.out.print("What would you like to do? ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    List<Room> available = reservationService.getAvailableRooms();
                    if (available.isEmpty()) {
                        System.out.println("\nNo rooms are currently available.");
                    } else {
                        System.out.println("\nAvailable Rooms:");
                        available.forEach(room -> System.out.printf(
                            "  [%d] %-12s - %s room, capacity %d%n",
                            room.getRoomId(),
                            room.getRoomName(),
                            room.getRoomType(),
                            room.getCapacity()
                        ));
                    }
                    break;
                }

                case 2: {
                    List<Reservation> reservations = reservationService.getReservations();
                    if (reservations.isEmpty()) {
                        System.out.println("\nNo room reservations on record.");
                    } else {
                        System.out.println("\nRoom Reservations:");
                        System.out.printf("  %-5s %-12s %-20s %-25s %s%n", "ID", "Room", "Date", "Name", "Email");
                        System.out.println("  " + "-".repeat(75));
                        reservations.forEach(r -> System.out.printf(
                            "  [%d] %-12s %-20s %-25s %s%n",
                            r.getRoom().getRoomId(),
                            r.getRoom().getRoomName(),
                            r.getReservationDate(),
                            r.getUser().getName(),
                            r.getUser().getEmail()
                        ));
                    }
                    break;
                }

                case 3: {
                    List<Room> available = reservationService.getAvailableRooms();
                    if (available.isEmpty()) {
                        System.out.println("\nSorry, there are no rooms available to reserve right now.");
                        break;
                    }

                    System.out.println("\nAvailable Rooms:");
                    available.forEach(room -> System.out.printf(
                        "  [%d] %-12s - %s room, capacity %d%n",
                        room.getRoomId(),
                        room.getRoomName(),
                        room.getRoomType(),
                        room.getCapacity()
                    ));

                    System.out.print("\nEnter room number to reserve: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Reservation date (e.g. 2026-06-20): ");
                    String date = scanner.nextLine();

                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    reservationService.reserveRoom(roomId, new Member(firstName, lastName, email), date);
                    break;
                }

                case 4: {
                    List<Reservation> reservations = reservationService.getReservations();
                    if (reservations.isEmpty()) {
                        System.out.println("\nNo room reservations to cancel.");
                        break;
                    }

                    System.out.println("\nCurrent Room Reservations:");
                    reservations.forEach(r -> System.out.printf(
                        "  [%d] %-12s - %s (%s)%n",
                        r.getRoom().getRoomId(),
                        r.getRoom().getRoomName(),
                        r.getUser().getName(),
                        r.getReservationDate()
                    ));

                    System.out.print("\nEnter room ID to cancel reservation: ");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    reservationService.cancelRoomReservation(roomId);
                    break;
                }

                case 5: {
                    List<Book> allBooks = library.getListOfBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("\nThe catalog is empty.");
                    } else {
                        System.out.println("\nBook Catalog:");
                        System.out.printf("  %-35s %-20s %-15s %-10s %s%n", "Title", "Author", "Genre", "Status", "Condition");
                        System.out.println("  " + "-".repeat(90));
                        allBooks.forEach(book -> System.out.printf(
                            "  %-35s %-20s %-15s %-10s %s%n",
                            book.getTitle(),
                            book.getAuthor(),
                            book.getGenre(),
                            book.getStatus(),
                            book.getCondition()
                        ));
                    }
                    break;
                }

                case 6: {
                    System.out.println("\nThank you for donating a book!");
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();

                    System.out.print("Condition (1. New / 2. Good / 3. Damaged): ");
                    int conditionChoice = scanner.nextInt();
                    scanner.nextLine();
                    BookCondition condition = switch (conditionChoice) {
                        case 2 -> BookCondition.GOOD;
                        case 3 -> BookCondition.DAMAGED;
                        default -> BookCondition.NEW;
                    };

                    library.addBook(new Book(title, author, genre, condition));
                    break;
                }

                case 7: {
                    List<Book> available = library.getAvailableBooks();
                    if (available.isEmpty()) {
                        System.out.println("\nNo books are currently available to borrow.");
                        break;
                    }

                    System.out.println("\nAvailable Books:");
                    available.forEach(book -> System.out.printf(
                        "  %-35s %s (%s)%n",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre()
                    ));

                    System.out.print("\nEnter the title of the book you'd like to borrow: ");
                    String title = scanner.nextLine();

                    System.out.print("Are you a member or visitor? (1. Member / 2. Visitor): ");
                    int roleChoice = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    User borrower = (roleChoice == 1)
                        ? new Member(firstName, lastName, email)
                        : new Visitor(firstName, lastName, email);

                    library.borrowBook(title, borrower, LocalDate.now());
                    break;
                }

                case 8: {
                    System.out.print("\nWhat is the title of the book you're returning? ");
                    String title = scanner.nextLine();
                    library.returnBook(title, LocalDate.now());
                    break;
                }

                case 9: {
                    List<Book> available = library.getAvailableBooks();
                    if (available.isEmpty()) {
                        System.out.println("\nNo books are currently available to reserve.");
                        break;
                    }

                    System.out.println("\nAvailable Books:");
                    available.forEach(book -> System.out.printf(
                        "  %-35s %s (%s)%n",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre()
                    ));

                    System.out.print("\nEnter the title of the book to reserve: ");
                    String title = scanner.nextLine();

                    System.out.print("Reservation date (e.g. 2026-06-20): ");
                    String date = scanner.nextLine();

                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    library.reserveBook(title, new Member(firstName, lastName, email), date);
                    break;
                }

                case 10: {
                    List<BookReservation> bookReservations = library.getBookReservations();
                    if (bookReservations.isEmpty()) {
                        System.out.println("\nNo book reservations to cancel.");
                        break;
                    }

                    System.out.println("\nCurrent Book Reservations:");
                    bookReservations.forEach(r -> System.out.printf(
                        "  %-35s %s (%s)%n",
                        r.getBook().getTitle(),
                        r.getUser().getName(),
                        r.getReservationDate()
                    ));

                    System.out.print("\nEnter the title of the book reservation to cancel: ");
                    String title = scanner.nextLine();
                    library.cancelBookReservation(title);
                    break;
                }

                case 11: {
                    List<Book> allBooks = library.getListOfBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("\nThe catalog is empty.");
                        break;
                    }

                    System.out.println("\nAll Books:");
                    allBooks.forEach(book -> System.out.printf(
                        "  %-35s %s (%s) - %s%n",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getStatus()
                    ));

                    System.out.print("\nEnter the title of the book to mark as lost: ");
                    String title = scanner.nextLine();
                    library.markAsLost(title, LocalDate.now());
                    break;
                }

                case 12: {
                    System.out.println("\nGreat! Let's get you registered as a member.");
                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    userService.addMember(new Member(firstName, lastName, email));
                    break;
                }

                case 13: {
                    System.out.println("\nWelcome! Let's note down your visit.");
                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    userService.addVisitor(new Visitor(firstName, lastName, email));
                    break;
                }

                case 14: {
                    System.out.println("\nRegistering a new staff member.");
                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    userService.addStaff(new Staff(firstName, lastName, email));
                    break;
                }

                case 15: {
                    System.out.println("\nRegistering a new admin.");
                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    userService.addAdmin(new Admin(firstName, lastName, email));
                    break;
                }

                case 16: {
                    System.out.print("\nEnter your email to look up fines: ");
                    String email = scanner.nextLine();

                    List<Fine> fines = fineService.getFinesByEmail(email);
                    if (fines.isEmpty()) {
                        System.out.println("No fines on record for " + email + ".");
                        break;
                    }

                    double pending = fineService.getPendingBalanceByEmail(email);
                    System.out.println("\nFines for " + email + ":");
                    System.out.printf("  %-4s %-16s %-12s %-10s %s%n", "#", "Reason", "Amount", "Status", "Date");
                    System.out.println("  " + "-".repeat(60));
                    for (int i = 0; i < fines.size(); i++) {
                        Fine f = fines.get(i);
                        System.out.printf("  %-4d %-16s $%-11.2f %-10s %s%n",
                            i + 1,
                            f.getReason(),
                            f.getAmount(),
                            f.getStatus(),
                            f.getIssuedDate()
                        );
                    }
                    System.out.printf("%n  Pending balance: $%.2f%n", pending);
                    break;
                }

                case 17: {
                    System.out.print("\nEnter your email to pay a fine: ");
                    String email = scanner.nextLine();

                    List<Fine> pending = fineService.getPendingFinesByEmail(email);
                    if (pending.isEmpty()) {
                        System.out.println("No pending fines for " + email + ".");
                        break;
                    }

                    System.out.println("\nPending fines for " + email + ":");
                    for (int i = 0; i < pending.size(); i++) {
                        Fine f = pending.get(i);
                        System.out.printf("  [%d] %s — $%.2f (issued %s)%n",
                            i + 1,
                            f.getReason(),
                            f.getAmount(),
                            f.getIssuedDate()
                        );
                    }

                    System.out.print("\nEnter fine number to pay (0 to cancel): ");
                    int fineChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (fineChoice < 1 || fineChoice > pending.size()) {
                        System.out.println("Cancelled.");
                        break;
                    }
                    fineService.payFine(pending.get(fineChoice - 1));
                    break;
                }

                case 18: {
                    System.out.print("\nSearch by title, author, or genre: ");
                    String query = scanner.nextLine();

                    List<Book> results = library.searchBooks(query);
                    if (results.isEmpty()) {
                        System.out.println("No books matched \"" + query + "\".");
                        break;
                    }

                    System.out.println("\nSearch Results:");
                    System.out.printf("  %-35s %-20s %-15s %-10s %s%n", "Title", "Author", "Genre", "Status", "Condition");
                    System.out.println("  " + "-".repeat(90));
                    results.forEach(book -> System.out.printf(
                        "  %-35s %-20s %-15s %-10s %s%n",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getStatus(),
                        book.getCondition()
                    ));
                    break;
                }

                case 19:
                    running = false;
                    break;

                default:
                    System.out.println("That's not a valid option. Please choose 1-19.");
                    break;
            }
        }

        scanner.close();
        System.out.println("\nThanks for visiting. See you next time!");
    }
}
