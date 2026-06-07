package src;

import java.util.ArrayList;
import java.util.List;
import src.constants.RoomType;

public class ReservationService {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public ReservationService() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();

        rooms.add(new Room(1, "Discovery", 10, RoomType.GROUP));
        rooms.add(new Room(2, "Endeavour", 8, RoomType.GROUP));
        rooms.add(new Room(3, "Capsule", 1, RoomType.QUIET));
        rooms.add(new Room(4, "Moon", 1, RoomType.QUIET));
        rooms.add(new Room(5, "Valley", 1, RoomType.QUIET));
    }

    public List<Room> getAvailableRooms() {
        return rooms.stream()
            .filter(Room::getIsAvailable)
            .toList();
    }

    public void reserveRoom(int roomId, User user, String reservationDate) {
        for(Room room:rooms) {
            if (room.getRoomId() == roomId && room.getIsAvailable()) {
                room.setIsAvailable(false);

                Reservation reservation = new Reservation(room, user, reservationDate);
                reservations.add(reservation);

                System.out.println("Room reserved successfully: " + roomId);
                return;
            }
        }

        System.out.println("Room Unavailable");
    }
}
