package src;

public class Reservation {
    private Room room;
    private int reservationId;
    private User user;
    private String reservationDate;

    public Reservation(Room room, int roomId, int reservationId, User user, String reservationDate) {
        this.room = room;
        this.reservationId = reservationId;
        this.user = user;
        this.reservationDate = reservationDate;
    }

    public void reserveRoom() {

    }

    public Room getRoom() {
        return room;
    }

    public int getReservationId() {
        return reservationId;
    }

    public User getUser() {
        return user;
    }

    public String getReservationDate() {
        return reservationDate;
    }
}
