package src.fines;

public class MemberFinePolicy implements FinePolicy {
    private static final double DAILY_RATE = 0.50;
    private static final double LOST_BOOK_FEE = 20.00;
    private static final int REVOCATION_FINE_COUNT = 3;
    private static final double REVOCATION_BALANCE = 20.00;

    @Override
    public double calculateOverdueFine(long daysOverdue) {
        return daysOverdue * DAILY_RATE;
    }

    @Override
    public double getLostBookFee() { return LOST_BOOK_FEE; }

    @Override
    public int getRevocationFineThreshold() { return REVOCATION_FINE_COUNT; }

    @Override
    public double getRevocationBalanceThreshold() { return REVOCATION_BALANCE; }
}
