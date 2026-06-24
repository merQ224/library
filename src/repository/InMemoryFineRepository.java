package src.repository;

import src.Fine;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryFineRepository implements FineRepository {
    private final List<Fine> fines = new ArrayList<>();

    @Override
    public void save(Fine fine) { fines.add(fine); }

    @Override
    public List<Fine> findAll() { return List.copyOf(fines); }

    @Override
    public List<Fine> findByEmail(String email) {
        return fines.stream()
            .filter(f -> f.getUser().getEmail().equalsIgnoreCase(email))
            .collect(Collectors.toList());
    }

    @Override
    public List<Fine> findPendingByEmail(String email) {
        return fines.stream()
            .filter(f -> f.getUser().getEmail().equalsIgnoreCase(email) && f.isPending())
            .collect(Collectors.toList());
    }
}
