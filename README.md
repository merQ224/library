# Library Management System

A terminal-based library system built in Java to practice OOP concepts — encapsulation, inheritance, composition, and single responsibility.

## How to Run

No build tools required. Compile and run from the project root:

```bash
javac -d out $(find src -name "*.java")
java -cp out src.Main
```

## Features

- Browse, borrow, return, reserve, and report books as lost
- Room reservations with cancellation
- User registration by role: Member, Visitor, Staff, Admin

## Folder Structure

```
src/
├── constants/          # Enums (BookStatus, RoomType, UserRole)
├── repository/         # Data access interfaces + in-memory implementations
├── Main.java           # Entry point — menu loop
├── Library.java        # Book operations and book reservations
├── ReservationService  # Room operations and room reservations
├── UserService.java    # User registration by role
├── Book.java           # Title, author, genre, status
├── Room.java           # Room details and availability
├── User.java           # Abstract base; subclasses: Admin, Staff, Member, Visitor
├── Reservation.java    # Links a room, user, and date
└── BookReservation.java# Links a book, user, and date
```

## Architecture

The app has three independent services that `Main` coordinates:

- **`Library`** — manages the book catalog. Each book has a `BookStatus` (Available, Borrowed, Reserved, Lost). Book reservations are stored here.
- **`ReservationService`** — manages rooms. Rooms have a type (Quiet, Group, Computer) and are marked unavailable when reserved.
- **`UserService`** — registers users by role. `User` is an abstract class with four subclasses (`Admin`, `Staff`, `Member`, `Visitor`), each returning its `UserRole`.

All data is in-memory — nothing persists between runs. Seed books and rooms are defined in `InMemoryBookRepository` and `InMemoryRoomRepository`.

## Planned Enhancements

- Book condition (New, Good, Damaged)
- Fines and penalties
- Search catalog
