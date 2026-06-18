package src;

import src.repository.RoomRepository;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final RoomRepository roomRepository;
    private final List<Reservation> reservations = new ArrayList<>();

    public ReservationService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findAll().stream()
            .filter(Room::getIsAvailable)
            .toList();
    }

    public void reserveRoom(int roomId, User user, String reservationDate) {
        roomRepository.findById(roomId)
            .filter(Room::getIsAvailable)
            .ifPresentOrElse(
                room -> {
                    room.setIsAvailable(false);
                    reservations.add(new Reservation(room, user, reservationDate));
                    System.out.println("Room reserved successfully: " + roomId);
                },
                () -> System.out.println("Room Unavailable")
            );
    }
}
