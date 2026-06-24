package src;

import src.constants.FineReason;
import src.constants.MembershipStatus;
import src.fines.FinePolicy;
import src.fines.FinePolicyFactory;
import src.repository.BorrowRecordRepository;
import src.repository.FineRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FineService {
    private static final int LOAN_DAYS = 14;

    private final FineRepository fineRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final Set<String> revokedEmails = new HashSet<>();

    public FineService(FineRepository fineRepository, BorrowRecordRepository borrowRecordRepository) {
        this.fineRepository = fineRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public BorrowRecord createBorrowRecord(Book book, User borrower, LocalDate date) {
        BorrowRecord record = new BorrowRecord(book, borrower, date, LOAN_DAYS);
        borrowRecordRepository.save(record);
        System.out.println("  Due date: " + record.getDueDate());
        return record;
    }

    public BorrowRecord findActiveBorrowByBookTitle(String title) {
        return borrowRecordRepository.findActiveBorrowByBookTitle(title).orElse(null);
    }

    public void processReturn(BorrowRecord record, LocalDate returnDate) {
        record.close(returnDate);
        if (!record.isOverdue(returnDate)) return;

        long days = record.daysOverdue(returnDate);
        FinePolicy policy = FinePolicyFactory.forUser(record.getBorrower());
        double amount = policy.calculateOverdueFine(days);

        Fine fine = new Fine(record.getBorrower(), amount, FineReason.OVERDUE_RETURN, returnDate);
        fineRepository.save(fine);
        System.out.printf("  Overdue by %d day(s) — fine issued: $%.2f%n", days, amount);
        checkAndHandleRevocation(record.getBorrower());
    }

    public void issueLostBookFine(BorrowRecord record, LocalDate date) {
        record.close(date);
        FinePolicy policy = FinePolicyFactory.forUser(record.getBorrower());
        double amount = policy.getLostBookFee();

        Fine fine = new Fine(record.getBorrower(), amount, FineReason.LOST_BOOK, date);
        fineRepository.save(fine);
        System.out.printf("  Lost book fine issued to %s: $%.2f%n", record.getBorrower().getName(), amount);
        checkAndHandleRevocation(record.getBorrower());
    }

    public boolean isRevoked(String email) {
        return revokedEmails.contains(email.toLowerCase());
    }

    public List<Fine> getFinesByEmail(String email) {
        return fineRepository.findByEmail(email);
    }

    public List<Fine> getPendingFinesByEmail(String email) {
        return fineRepository.findPendingByEmail(email);
    }

    public double getPendingBalanceByEmail(String email) {
        return fineRepository.findPendingByEmail(email).stream()
            .mapToDouble(Fine::getAmount)
            .sum();
    }

    public boolean payFine(Fine fine) {
        if (!fine.isPending()) {
            System.out.println("This fine has already been settled.");
            return false;
        }
        fine.markAsPaid();
        System.out.printf("  Fine of $%.2f paid successfully.%n", fine.getAmount());
        return true;
    }

    private void checkAndHandleRevocation(User user) {
        if (!(user instanceof Member member)) return;
        if (member.getMembershipStatus() == MembershipStatus.REVOKED) return;

        FinePolicy policy = FinePolicyFactory.forUser(user);
        List<Fine> pending = fineRepository.findPendingByEmail(user.getEmail());
        double balance = pending.stream().mapToDouble(Fine::getAmount).sum();

        if (pending.size() >= policy.getRevocationFineThreshold()
                || balance >= policy.getRevocationBalanceThreshold()) {
            member.setMembershipStatus(MembershipStatus.REVOKED);
            revokedEmails.add(user.getEmail().toLowerCase());
            System.out.printf(
                "  *** MEMBERSHIP REVOKED: %s — %d unpaid fine(s) totalling $%.2f ***%n",
                member.getName(), pending.size(), balance
            );
        }
    }
}
