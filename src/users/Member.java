package src.users;

import src.constants.MembershipStatus;
import src.constants.UserRole;

public class Member extends User {
    private MembershipStatus membershipStatus = MembershipStatus.ACTIVE;

    public Member(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    @Override
    public UserRole getRole() { return UserRole.MEMBER; }

    public MembershipStatus getMembershipStatus() { return membershipStatus; }

    public void setMembershipStatus(MembershipStatus status) { this.membershipStatus = status; }
}
