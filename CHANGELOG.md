# Changelog

All notable changes to this project will be documented here.

Format: [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)

## [Unreleased]

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
