package Liberary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BorrowingRecord {
    private final int id;
    private final Member member;
    private final Book book;
    private final LocalDateTime BorrowingDate;
    private LocalDateTime returnDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static int count = 1;

    private synchronized int generateId(){
        return count++;
    }
    

    public BorrowingRecord(Member member, Book book) {
        this.id = generateId();
        this.member = member;
        this.book = book;
        this.BorrowingDate = LocalDateTime.now();
    }


    public Member getMember() {
        return member;
    }


    public Book getBook() {
        return book;
    }


    public LocalDateTime getBorrowingDate() {
        return BorrowingDate;
    }


    @Override
    public String toString() {
        String returned;
        if(returnDate == null) 
            returned = "Not returned Yet";
        else 
            returned = returnDate.format(formatter);
            return "id=" + id + " :  member=" + member.getFull_name() + ", book=" + book.getTitle() + ", BorrowingDate="
                + BorrowingDate.format(formatter) + ", returnDate=" + returned;
    }


    public LocalDateTime getReturnDate() {
        return returnDate;
    }


    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    

}
