package src;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    // static: shared by the class
    private static final Logger logger = Logger.getLogger(Main.class.getName());

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
                case 1:
                    reservationService.getAvailableRooms()
                        .forEach(room -> System.out.println("Name: " + room.getRoomName() + ", ID: " + room.getRoomId()));
                    break;


                case 2:
                    System.out.println("Enter Room ID: ");
                    int roomId = scanner.nextInt();

                    System.out.println("Enter Reservation Date: ");
                    scanner.nextLine();
                    String date = scanner.nextLine();
                    reservationService.reserveRoom(roomId, user, date);
                    break;
                
                case 3:
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
        }

        scanner.close();
    }
}
