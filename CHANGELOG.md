# Changelog

All notable changes to this project will be documented here.

Format: [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)

## [Unreleased]

## [0.5.0] - 2026-06-24
### Added
- `UserRole` enum (`ADMIN`, `STAFF`, `MEMBER`, `VISITOR`) in `src/constants/`
- `Admin` and `Staff` subclasses of `User`
- `UserService.addAdmin` and `UserService.addStaff` with dedicated tracking lists
- "Register as staff" and "Register as admin" menu options

### Changed
- `User` declares abstract `getRole()` — each subclass returns its `UserRole`

## [0.4.0] - 2026-06-24
### Added
- `BookReservation` class linking a book, user, and date
- `Library.reserveBook` — sets book status to `RESERVED` and records the reservation
- `Library.cancelBookReservation` — removes the reservation and restores status to `AVAILABLE`
- `Library.getBookReservations` — returns all active book reservations
- `ReservationService.getReservations` — returns all active room reservations
- `ReservationService.cancelRoomReservation` — cancels a room reservation and marks the room available again
- "View room reservations", "Cancel room reservation", "Reserve a book", "Cancel book reservation" menu options

## [0.3.0] - 2026-06-23
### Added
- `BookStatus` enum (`AVAILABLE`, `BORROWED`, `RESERVED`, `LOST`) in `src/constants/`
- `Library.markAsLost(String title)` to flag a book as lost
- "Browse book catalog" menu option — shows all books with their current status
- "Report a book as lost" menu option

### Changed
- `Book` now tracks status via `BookStatus` instead of a boolean `isAvailable` field
- `Library.borrowBook` sets status to `BORROWED`; `returnBook` sets status back to `AVAILABLE`

## [0.2.0] - 2026-06-22
### Added
- `ReservationService` for room reservations
- `Room` and `Reservation` classes; `RoomType` enum (`QUIET`, `GROUP`, `COMPUTER`)
- Repository pattern: `BookRepository`, `InMemoryBookRepository`, `RoomRepository`, `InMemoryRoomRepository`
- `UserService` for registering members and visitors
- `Member` and `Visitor` as subclasses of `User`

### Changed
- `Library` refactored to follow SRP; user tracking moved to `UserService`
- Seed data moved from constructors into repository classes

## [0.1.0] - 2026-06-21
### Added
- Initial project: `Library`, `Book`, `User` classes
- Menu-driven CLI in `Main` (borrow, return, donate, register)
