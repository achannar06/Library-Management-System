package Liberary;

import java.time.LocalDateTime;

public class BorrowingRecord {
    private final int id;
    private final Member member;
    private final Book book;
    private final LocalDateTime BorrowingDate;
    private LocalDateTime returnDate;
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
        return "BorrowingRecord [id=" + id + ", member=" + member + ", book=" + book + ", BorrowingDate="
                + BorrowingDate + ", returnDate=" + returnDate + "]";
    }


    public LocalDateTime getReturnDate() {
        return returnDate;
    }


    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    

}
