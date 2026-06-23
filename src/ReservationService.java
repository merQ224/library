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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void reserveRoom(int roomId, User user, String reservationDate) {
        roomRepository.findById(roomId)
            .filter(Room::getIsAvailable)
            .ifPresentOrElse(
                room -> {
                    room.setIsAvailable(false);
                    reservations.add(new Reservation(room, user, reservationDate));
                    System.out.println("Room reserved successfully: " + room.getRoomName());
                },
                () -> System.out.println("Room " + roomId + " is unavailable.")
            );
    }

    public void cancelRoomReservation(int roomId) {
        Reservation reservation = reservations.stream()
            .filter(r -> r.getRoom().getRoomId() == roomId)
            .findFirst()
            .orElse(null);

        if (reservation == null) {
            System.out.println("No reservation found for room " + roomId + ".");
            return;
        }
        reservation.getRoom().setIsAvailable(true);
        reservations.remove(reservation);
        System.out.println("Reservation for room \"" + reservation.getRoom().getRoomName() + "\" has been cancelled.");
    }
}
