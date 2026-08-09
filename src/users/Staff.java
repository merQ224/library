package src.users;

import src.constants.UserRole;

public class Staff extends User {
    public Staff(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    @Override
    public UserRole getRole() {
        return UserRole.STAFF;
    }
}
