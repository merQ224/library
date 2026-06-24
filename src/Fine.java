package src;

import src.constants.FineReason;
import src.constants.FineStatus;
import java.time.LocalDate;

public class Fine {
    private final User user;
    private final double amount;
    private final FineReason reason;
    private final LocalDate issuedDate;
    private FineStatus status;

    public Fine(User user, double amount, FineReason reason, LocalDate issuedDate) {
        this.user = user;
        this.amount = amount;
        this.reason = reason;
        this.issuedDate = issuedDate;
        this.status = FineStatus.PENDING;
    }

    public User getUser() { return user; }
    public double getAmount() { return amount; }
    public FineReason getReason() { return reason; }
    public FineStatus getStatus() { return status; }
    public LocalDate getIssuedDate() { return issuedDate; }

    public boolean isPending() { return status == FineStatus.PENDING; }

    public void markAsPaid() { this.status = FineStatus.PAID; }

    public void waive() { this.status = FineStatus.WAIVED; }
}
