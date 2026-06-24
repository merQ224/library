package src.repository;

import src.Fine;
import java.util.List;

public interface FineRepository {
    void save(Fine fine);
    List<Fine> findAll();
    List<Fine> findByEmail(String email);
    List<Fine> findPendingByEmail(String email);
}
