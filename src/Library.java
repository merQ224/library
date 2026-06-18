package src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Visitor> visitors;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.visitors = new ArrayList<>();

        books.add(1, new Book("Apples", "Peanut", "Comedy"));
        books.add(2, new Book("Peaches", "Pan", "Romance"));
        books.add(3, new Book("To Kill a Mockingbird", "Harper Lee", "Novel"));
        books.add(4, new Book("The Art of Money", "Peanut", "Non-fiction"));
        books.add(5, new Book("The Art Of War", "Sun Tzu", "Non-fiction"));
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Successfully added: " + book.getTitle());
    }

    public void borrowBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && book.getIsAvailable()) {
            book.setAvailable(false);
            System.out.println("You have borrowed: " + book.getTitle());
        } else {
            System.out.println("Book: " + title + " is not available!");
        }
    }

    public void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && !book.getIsAvailable()) {
            book.setAvailable(true);
            System.out.println("You have returned: " + book.getTitle());
        } else {
            System.out.println("Book: " + title + " was not borrowed!");
        }
    }

    public List<Book> getListOfBooks() {
        return books;
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
            .filter(book -> book.getIsAvailable() == true)
            .collect(Collectors.toList());
    }

    public Book findBookByTitle(String title) {
        System.out.println("Find book by title: " + title);
        return books.stream()
            .filter(book -> book.getTitle().equals(title))
            .findFirst()
            .orElse(null);
    }

    public int getTotalNumberOfBooks() {
        return books.size();
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