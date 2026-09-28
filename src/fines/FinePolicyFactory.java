package src.fines;

import src.constants.UserRole;
import src.users.User;

public class FinePolicyFactory {
    public static FinePolicy forUser(User user) {
        if (user.getRole() == UserRole.MEMBER) {
            return new MemberFinePolicy();
        }
        return new VisitorFinePolicy();
    }
}
