package src.repository;

import src.rooms.Room;
import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    List<Room> findAll();
    Optional<Room> findById(int id);
}
