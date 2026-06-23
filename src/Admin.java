package src;

import src.constants.UserRole;

public class Admin extends User {
    public Admin(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    @Override
    public UserRole getRole() {
        return UserRole.ADMIN;
    }
}
