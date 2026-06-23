package src;
import src.repository.InMemoryBookRepository;
import src.repository.InMemoryRoomRepository;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ReservationService reservationService = new ReservationService(new InMemoryRoomRepository());
        Library library = new Library(new InMemoryBookRepository());
        UserService userService = new UserService();
        boolean running = true;

        while (running) {
            System.out.println("\n=============================");
            System.out.println("   Welcome to the Library    ");
            System.out.println("=============================");
            System.out.println("  1. Browse available rooms");
            System.out.println("  2. Reserve a room");
            System.out.println("  3. Browse book catalog");
            System.out.println("  4. Donate a book");
            System.out.println("  5. Borrow a book");
            System.out.println("  6. Return a book");
            System.out.println("  7. Report a book as lost");
            System.out.println("  8. Register as a member");
            System.out.println("  9. Sign in as a visitor");
            System.out.println(" 10. Exit");
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

                case 3: {
                    List<Book> allBooks = library.getListOfBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("\nThe catalog is empty.");
                    } else {
                        System.out.println("\nBook Catalog:");
                        System.out.printf("  %-30s %-20s %-15s %s%n", "Title", "Author", "Genre", "Status");
                        System.out.println("  " + "-".repeat(80));
                        allBooks.forEach(book -> System.out.printf(
                            "  %-30s %-20s %-15s %s%n",
                            book.getTitle(),
                            book.getAuthor(),
                            book.getGenre(),
                            book.getStatus()
                        ));
                    }
                    break;
                }

                case 4: {
                    System.out.println("\nThank you for donating a book!");
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();

                    library.addBook(new Book(title, author, genre));
                    break;
                }

                case 5: {
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
                    library.borrowBook(title);
                    break;
                }

                case 6: {
                    System.out.print("\nWhat is the title of the book you're returning? ");
                    String title = scanner.nextLine();
                    library.returnBook(title);
                    break;
                }

                case 7: {
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
                    library.markAsLost(title);
                    break;
                }

                case 8: {
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

                case 9: {
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

                case 10:
                    running = false;
                    break;

                default:
                    System.out.println("That's not a valid option. Please choose 1-10.");
                    break;
            }
        }

        scanner.close();
        System.out.println("\nThanks for visiting. See you next time!");
    }
}
