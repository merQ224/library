package src.users;

import src.constants.UserRole;

public class Visitor extends User {
    public Visitor(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    @Override
    public UserRole getRole() {
        return UserRole.VISITOR;
    }
}
