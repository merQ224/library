package src;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private boolean isMember;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(boolean member) {
        isMember = member;
    }
}
