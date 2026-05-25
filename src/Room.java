package src;

import src.constants.RoomType;

public class Room {
    private int roomId;
    private String roomName;
    private int capacity;
    private boolean isAvailable;
    private RoomType roomType;

    public Room(int roomId, String roomName, int capacity, RoomType roomType) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.isAvailable = true;
        this.roomType = roomType;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public String getRoomType() {
        return roomType.name();
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }
}
