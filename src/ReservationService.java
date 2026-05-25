package src;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import src.constants.RoomType;

public class ReservationService {
    private static final Logger logger = Logger.getLogger(Reservation.class.getName());
    private List<Room> rooms;
    private List<Reservation> reservations;

    public ReservationService() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();

        rooms.add(new Room(1, "Discovery", 10, RoomType.GROUP));
        rooms.add(new Room(2, "Endeavour", 8, RoomType.GROUP));
        rooms.add(new Room(3, "Discovery", 1, RoomType.QUIET));
        rooms.add(new Room(4, "Discovery", 1, RoomType.QUIET));
        rooms.add(new Room(5, "Discovery", 1, RoomType.QUIET));
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

                logger.info("Room reserved successfully: " + roomId);
                return;
            }
        }

        logger.info("Room Unavailable");
    }
}
