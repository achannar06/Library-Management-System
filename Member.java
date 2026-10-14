package Liberary;

public class Member {
    private final int id;
    private String full_name;
    private String email;
    private String phone_number;
    private int BorrowsLeft = 3;
    private static int count = 1;
    private static synchronized int generateId(){
        return count++;
    }
    
    public Member(String full_name, String email, String phone_number) {
        this.id = generateId();
        this.full_name = full_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }
    public String getFull_name() {
        return full_name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    
    public int getBorrowsLeft(){
        return BorrowsLeft;
    }
    public void borrowBook(){
        BorrowsLeft--;
    }

    public void returnBook(){
        BorrowsLeft++;
    }

    @Override
    public String toString() {
        return "Member [id=" + id + ", full_name=" + full_name + ", email=" + email + ", phone_number=" + phone_number
                + "]";
    } 
}
