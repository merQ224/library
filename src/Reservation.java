package src;

public class Reservation {
    private Room room;
    private User user;
    private String reservationDate;

    public Reservation(Room room, User user, String reservationDate) {
        this.room = room;
        this.user = user;
        this.reservationDate = reservationDate;
    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }

    public String getReservationDate() {
        return reservationDate;
    }
}
