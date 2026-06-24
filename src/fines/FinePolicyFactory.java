package src.fines;

import src.User;
import src.constants.UserRole;

public class FinePolicyFactory {
    public static FinePolicy forUser(User user) {
        if (user.getRole() == UserRole.MEMBER) {
            return new MemberFinePolicy();
        }
        return new VisitorFinePolicy();
    }
}
