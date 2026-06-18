package src;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    // main() needs to run before any objects exist
    public static void main(String[] args) {
        // Read input from keyboard
        Scanner scanner = new Scanner(System.in);

        ReservationService reservationService = new ReservationService();
        Library library = new Library();
        boolean running = true;

        while(running) {
            System.out.println("\n=== Library Management System ===");

            System.out.println("1. View Available Rooms");
            System.out.println("2. Reserve Room");
            System.out.println("3. Add Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Add Member");
            System.out.println("7. Add Visitor");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1: // View available rooms 
                    reservationService.getAvailableRooms()
                        .forEach(room -> System.out.println("Name: " + room.getRoomName() + ", ID: " + room.getRoomId()));
                    break;


                case 2: { // Reserve a room
                    System.out.println("Enter Room ID: ");
                    int roomId = scanner.nextInt();

                    System.out.println("Enter Reservation Date: ");
                    scanner.nextLine();
                    String date = scanner.nextLine();
                    
                    System.out.println("Enter First name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter Email: ");
                    String email = scanner.nextLine();

                    Member member = new Member(firstName, lastName, email);

                    reservationService.reserveRoom(roomId, member, date);
                    break;
                }

                case 3: { // Add a book
                    System.out.println("Enter book title: ");
                    String title = scanner.nextLine();

                    System.out.println("Enter author: ");
                    String author = scanner.nextLine();

                    System.out.println("Enter genre: ");
                    String genre = scanner.nextLine();

                    Book book = new Book(title, author, genre);
                    library.addBook(book);
                    break;
                }

                case 4: {// Borrow a book
                    List<String> availableBooksByTitle = library.getAvailableBooks().stream()
                        .map(Book::getTitle)
                        .collect(Collectors.toList());

                    System.out.println("Available Books - title: ");
                    availableBooksByTitle.forEach(System.out::println);

                    System.out.println("Enter book title to borrow: ");
                    String title = scanner.nextLine();

                    library.borrowBook(title);
                    break;
                }

                case 5: // Return a book
                    System.out.println("Title of book being returned: ");
                    String title = scanner.nextLine();

                    library.returnBook(title);
                    break;

                case 6: { // Add a member
                    System.out.print("Recording Member: ");
                    System.out.print("First name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    Member user = new Member(firstName, lastName, email);
                    library.addMember(user);
                    break;
                }

                case 7: { // Add a visitor
                    System.out.print("Recording Visitor: ");
                    System.out.print("Enter First name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter Last name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    Visitor user = new Visitor(firstName, lastName, email);
                    library.addVisitor(user);
                    break;
                }

                case 8: // Exit
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }

        scanner.close();
        System.out.print("Cya!");
    }
}
