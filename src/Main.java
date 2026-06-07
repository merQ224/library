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
        User user = new User("James", "bond", "j.bond@gmail.com");
        boolean running = true;

        while(running) {
            System.out.println("\n=== Library Management System ===");

            System.out.println("1. View Available Rooms");
            System.out.println("2. Reserve Room");
            System.out.println("3. Add Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Find Book By ID");
            System.out.println("7. Add Member");
            System.out.println("8. Add Visitor");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();

            switch(choice) {
                case 1: // View available rooms 
                    reservationService.getAvailableRooms()
                        .forEach(room -> System.out.println("Name: " + room.getRoomName() + ", ID: " + room.getRoomId()));
                    break;


                case 2: // Reserve a room
                    System.out.println("Enter Room ID: ");
                    int roomId = scanner.nextInt();

                    System.out.println("Enter Reservation Date: ");
                    scanner.nextLine();
                    String date = scanner.nextLine();
                    reservationService.reserveRoom(roomId, user, date);
                    break;
                
                case 3: { // Add a book
                    System.out.println("Enter book title: ");
                    scanner.nextLine();
                    String title = scanner.nextLine();

                    System.out.println("Enter author: ");
                    String author = scanner.nextLine();

                    System.out.println("Enter genre: ");
                    String genre = scanner.nextLine();

                    Book book = new Book(title, author, genre);
                    library.addBook(book);
                    break;
                }

                case 4: // Borrow a book
                    List<String> availableBooksByTitle = library.getAvailableBooks().stream()
                        .map(Book::getTitle)
                        .collect(Collectors.toList());
                    scanner.nextLine();

                    System.out.println("Available Books - title: " + availableBooksByTitle);
                    System.out.println("Enter book title to borrow: ");
                    String title = scanner.nextLine();

                    library.borrowBook(title);
                    break;

                                        
                case 5: // Return a book
                case 6: // Find book by ID
                case 7: // Add a member
                case 8: // Add a visitor
                case 9: // Exit
            }
        }

        scanner.close();
    }
}
