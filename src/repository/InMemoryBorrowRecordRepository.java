package src.repository;

import src.books.BorrowRecord;
import src.users.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryBorrowRecordRepository implements BorrowRecordRepository {
    private final List<BorrowRecord> records = new ArrayList<>();

    @Override
    public void save(BorrowRecord record) { records.add(record); }

    @Override
    public List<BorrowRecord> findAll() { return List.copyOf(records); }

    @Override
    public List<BorrowRecord> findByUser(User user) {
        return records.stream()
            .filter(r -> r.getBorrower().getEmail().equals(user.getEmail()))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<BorrowRecord> findActiveBorrowByBookTitle(String title) {
        return records.stream()
            .filter(r -> r.getBook().getTitle().equalsIgnoreCase(title) && r.isActive())
            .findFirst();
    }
}
