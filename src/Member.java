package src;

import src.constants.UserRole;

public class Member extends User {
    public Member(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    @Override
    public UserRole getRole() {
        return UserRole.MEMBER;
    }
}
