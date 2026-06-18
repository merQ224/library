package src;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<Member> members;
    private List<Visitor> visitors;

    public UserService() {
        this.members = new ArrayList<>();
        this.visitors = new ArrayList<>();
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
        return members.size() + visitors.size();
    }

    public int getTotalMembers() {
        return members.size();
    }

    public int getTotalVisitors() {
        return visitors.size();
    }
}
