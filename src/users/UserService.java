package src.users;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<Admin> admins = new ArrayList<>();
    private final List<Staff> staff = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Visitor> visitors = new ArrayList<>();

    public void addAdmin(Admin admin) {
        admins.add(admin);
        System.out.println("Registered admin: " + admin.getName());
    }

    public void addStaff(Staff member) {
        staff.add(member);
        System.out.println("Registered staff: " + member.getName());
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Recorded member entrance: " + member.getName());
    }

    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
        System.out.println("Recorded visitor entrance: " + visitor.getName());
    }

    public int getTotalUsers() {
        return admins.size() + staff.size() + members.size() + visitors.size();
    }

    public int getTotalMembers() {
        return members.size();
    }

    public int getTotalVisitors() {
        return visitors.size();
    }
}
