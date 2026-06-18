package src.repository;

import src.Room;
import src.constants.RoomType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRoomRepository implements RoomRepository {
    private final List<Room> rooms = new ArrayList<>();

    public InMemoryRoomRepository() {
        rooms.add(new Room(1, "Discovery", 10, RoomType.GROUP));
        rooms.add(new Room(2, "Endeavour", 8, RoomType.GROUP));
        rooms.add(new Room(3, "Capsule", 1, RoomType.QUIET));
        rooms.add(new Room(4, "Moon", 1, RoomType.QUIET));
        rooms.add(new Room(5, "Valley", 1, RoomType.QUIET));
    }

    @Override
    public List<Room> findAll() {
        return rooms;
    }

    @Override
    public Optional<Room> findById(int id) {
        return rooms.stream()
            .filter(room -> room.getRoomId() == id)
            .findFirst();
    }
}
