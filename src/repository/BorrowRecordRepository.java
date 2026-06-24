package src.repository;

import src.BorrowRecord;
import src.User;
import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository {
    void save(BorrowRecord record);
    List<BorrowRecord> findAll();
    List<BorrowRecord> findByUser(User user);
    Optional<BorrowRecord> findActiveBorrowByBookTitle(String title);
}
