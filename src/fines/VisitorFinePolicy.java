package src.fines;

public class VisitorFinePolicy implements FinePolicy {
    private static final double DAILY_RATE = 1.00;
    private static final double LOST_BOOK_FEE = 30.00;

    @Override
    public double calculateOverdueFine(long daysOverdue) {
        return daysOverdue * DAILY_RATE;
    }

    @Override
    public double getLostBookFee() { return LOST_BOOK_FEE; }

    @Override
    public int getRevocationFineThreshold() { return Integer.MAX_VALUE; }

    @Override
    public double getRevocationBalanceThreshold() { return Double.MAX_VALUE; }
}
