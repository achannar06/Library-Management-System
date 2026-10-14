package Liberary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Liberary {
    private static final Map<Member , List<Book>> BooksBorrowed = new HashMap<>();
    private static final List<Book> allBooks = new ArrayList<>();
    private static final List<Member> AllMembers = new ArrayList<>();
    private static final List<BorrowingRecord> allRecords = new ArrayList<>();

    ////////////////////////////////////// BOOKS LOGIC //////////////////////////////////////
    public void addBook(String title, String author, String publication_year, Category category , int quantity){
        Book book = new Book(title , author , publication_year , category , quantity);
        allBooks.add(book);
    }
    public void displayAllBooks(){
        allBooks.forEach(System.out::println);
    }
    public Book getBookById(int id){
        Book res = allBooks.stream()
                    .filter(book -> book.getId() == id)
                    .findFirst()
                    .orElse(null);

        return res;
    }
    public Book getBookByTitle(String title){
        Book res = allBooks.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(title))
                    .findFirst()
                    .orElse(null);
        
        return res;
    }
    public void displayAvailableBooks(){
        List<Book> res = allBooks.stream()
                    .filter(book -> book.getAvailable() > 0)
                    .toList();

        res.forEach(System.out::println);
    }
    public List<Book> getBooksByCategory(Category cat){
        List<Book> res = allBooks.stream()
                                .filter(book -> book.getCategory() == cat)
                                .toList();
        
        return res;
    }
    ////////////////////////////////////// Members LOGIC ////////////////////////////////////
    public void addMember(String full_name, String email, String phone_number){
        Member member = new Member(full_name, email, phone_number);
        AllMembers.add(member);
    }
    public Member getMemberById(int id){
        Member res = AllMembers.stream()
                           .filter(member -> member.getId() == id)
                           .findFirst()
                           .orElse(null);
        
        return res;
    }
    public void displayAllMembers(){
        AllMembers.forEach(System.out::println);
    }
    public void displayBooksBorrowedByMember(Member m){
        BooksBorrowed.get(m).forEach(System.out::println);
    }
    ////////////////////////////////////// Liberary LOGIC ///////////////////////////////////
    public void Borrow(Member m , Book b) 
    throws BookLimitExceededException,BookUnavailableException,AlreadyBorrowedException{
        if(m == null || b == null) {
            System.out.println("The book or the member doesnt exist!");
            return;
        }

        if(m.getBorrowsLeft() == 0)
            throw new BookLimitExceededException("You have hit your limit!");
        if(b.getAvailable() == 0)
            throw new BookUnavailableException("This book is unavailable right now!");
        if(BooksBorrowed.containsKey(m) && BooksBorrowed.get(m).contains(b))
            throw new AlreadyBorrowedException("You already have borrowed this book!");

        if(!BooksBorrowed.containsKey(m)) BooksBorrowed.put(m , new ArrayList<>());
        BooksBorrowed.get(m).add(b);
        m.borrowBook();
        b.borrowBook();
        allRecords.add(new BorrowingRecord(m, b));
        System.out.println(m.getFull_name() + " has successfully Borrowed " + b.getTitle());
    }
    public BorrowingRecord getRecord(Member m , Book b){
        for(BorrowingRecord record : allRecords){
            if(record.getBook() == b && record.getMember() == m) return record;
        }

        return null;
    }
    public void returnB(Member m , Book b)
    throws BookNotBorrowedException,MemberNotBorrowingException{
        if(m == null || b == null) {
            System.out.println("The book or the member doesnt exist!");
            return;
        }

        if(!BooksBorrowed.containsKey(m))
            throw new MemberNotBorrowingException("This member doesn't have borrowing record!");
        if(!BooksBorrowed.get(m).contains(b))
            throw new BookNotBorrowedException("This member didn't borrow this book");

        BooksBorrowed.get(m).remove(b);
        b.returnBook();
        m.returnBook();
        BorrowingRecord thisRecord = getRecord(m, b);
        thisRecord.setReturnDate(LocalDateTime.now());
        System.out.println(m.getFull_name() + " has successfully returned " + b.getTitle());
    }
    ////////////////////////////////////// Statistics LOGIC /////////////////////////////////
    public int totalNumberOfBooks(){
        int res = 0;

        for(Book book : allBooks){
            res += book.getQuantity();
        }

        return res;
    }
    public int totalAvailableBooks(){
        int res = 0;

        for(Book book : allBooks){
            res += book.getAvailable();
        }

        return res;
    }
    public int totalBorrowedBooks(){
        int res = allRecords.stream()
                          .filter(record -> record.getReturnDate() == null)
                          .toList().size();

        return res;
    }
    public void MembersBorrowedBooks(){
        for(Member m : BooksBorrowed.keySet()){
            if(BooksBorrowed.get(m).size() > 0) System.out.println(m);
        }
    }
    public Member mostBorrowing(){
        int max = 0;
        Member res = null;
        for(Member m : BooksBorrowed.keySet()){
            if(max < BooksBorrowed.get(m).size()){
                max = BooksBorrowed.get(m).size();
                res = m;
            }
        }

        return res;
    }
    public void displayBooksSorted(){
        List<Book> sortedList = new ArrayList<>(allBooks);
        Collections.sort(sortedList);
        sortedList.forEach(System.out::println);
    }
}   
