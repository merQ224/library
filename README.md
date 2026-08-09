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
- Fines and penalties: role-based overdue and lost-book fees, with membership revocation after repeated unpaid fines

## Folder Structure

```
src/
├── books/              # Book, BookReservation, BorrowRecord, Library
├── users/              # User (abstract) + Admin, Staff, Member, Visitor, UserService
├── rooms/              # Room, Reservation, ReservationService
├── fines/              # Fine, FineService, FinePolicy strategy (FinePolicyFactory, MemberFinePolicy, VisitorFinePolicy)
├── constants/          # Enums (BookStatus, RoomType, UserRole, MembershipStatus, FineReason, FineStatus)
├── repository/         # Data access interfaces + in-memory implementations
└── Main.java           # Entry point — menu loop
```

## Architecture

The app has four independent services that `Main` coordinates:

- **`Library`** — manages the book catalog. Each book has a `BookStatus` (Available, Borrowed, Reserved, Lost). Book reservations are stored here, and borrow/return/lost flows delegate to `FineService`.
- **`ReservationService`** — manages rooms. Rooms have a type (Quiet, Group, Computer) and are marked unavailable when reserved.
- **`UserService`** — registers users by role. `User` is an abstract class with four subclasses (`Admin`, `Staff`, `Member`, `Visitor`), each returning its `UserRole`. `Member` also tracks `MembershipStatus` (Active/Revoked).
- **`FineService`** — tracks borrow records, issues fines for overdue returns and lost books, and revokes a member's status once unpaid fines cross a threshold. Fine amounts and thresholds come from a per-role `FinePolicy` (`MemberFinePolicy`, `VisitorFinePolicy`), chosen at runtime by `FinePolicyFactory`.

All data is in-memory — nothing persists between runs. Seed books and rooms are defined in `InMemoryBookRepository` and `InMemoryRoomRepository`.

## Planned Enhancements

- Book condition (New, Good, Damaged)
- Search catalog
