package src.fines;

public interface FinePolicy {
    double calculateOverdueFine(long daysOverdue);
    double getLostBookFee();
    int getRevocationFineThreshold();
    double getRevocationBalanceThreshold();
}
