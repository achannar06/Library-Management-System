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
        List<Book> res = allBooks.stream()
                                 .filter(book -> !book.getIsDeleted()).toList();

        if(res.isEmpty()) System.out.println("There are no books in this liberary!");
        else res.forEach(System.out::println);
    }
    public Book getBookById(int id){
        Book res = allBooks.stream()
                    .filter(book -> book.getId() == id && !book.getIsDeleted())
                    .findFirst()
                    .orElse(null);

        return res;
    }
    public Book getDeletedBookById(int id){
        Book res = allBooks.stream()
                    .filter(book -> book.getId() == id && book.getIsDeleted())
                    .findFirst()
                    .orElse(null);

        return res;
    }
    public Book getBookByTitle(String title){
        Book res = allBooks.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(title) && !book.getIsDeleted())
                    .findFirst()
                    .orElse(null);
        
        return res;
    }
    public void displayAvailableBooks(){
        List<Book> res = allBooks.stream()
                    .filter(book -> book.getAvailable() > 0 && !book.getIsDeleted())
                    .toList();

        if(res.isEmpty()) System.out.println("There are no books avaiblable currently");
        else res.forEach(System.out::println);
    }
    public List<Book> getBooksByCategory(Category cat){
        List<Book> res = allBooks.stream()
                                .filter(book -> book.getCategory() == cat && !book.getIsDeleted())
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
                           .filter(member -> member.getId() == id && !member.getIsDeleted())
                           .findFirst()
                           .orElse(null);
        
        return res;
    }
    public Member getDeletedMemberById(int id){
        Member res = AllMembers.stream()
                           .filter(member -> member.getId() == id && member.getIsDeleted())
                           .findFirst()
                           .orElse(null);
        
        return res;
    }
    public void displayAllMembers(){
        List<Member> res = AllMembers.stream()
                                 .filter(member -> !member.getIsDeleted()).toList();

        if(res.isEmpty()) System.out.println("There are no members registered yet!");
        else res.forEach(System.out::println);
    }
    public void displayBooksBorrowedByMember(Member m){
        if(BooksBorrowed.containsKey(m)){
            List<Book> bm = BooksBorrowed.get(m);
            if(bm.size() == 0) System.out.println("This member hasn't borrowed any books yet");
            else bm.forEach(System.out::println);
        }    
        else{
            System.out.println("This member hasn't borrowed any books yet");
        }
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
        System.out.println(m.getFull_name() + " has successfully Borrowed " + b.getTitle() + " , the available copies : " + b.getAvailable());
    }
    public List<BorrowingRecord> getRecord(Member m , Book b){
        if(m == null || b == null) return null;
        
        List<BorrowingRecord> res = allRecords.stream()
                                              .filter(record -> record.getBook() == b 
                                              && record.getMember() == m).toList();


        return res;
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
        List<BorrowingRecord> thisRecord = getRecord(m, b);
        BorrowingRecord activeRecord = thisRecord.stream()
                                                 .filter(record -> record.getReturnDate() == null)
                                                 .findFirst()
                                                 .orElse(null);
        activeRecord.setReturnDate(LocalDateTime.now());
        System.out.println(m.getFull_name() + " has successfully returned " + b.getTitle()  + " , the available copies : " + b.getAvailable());
    }
    public void returnAll(Member m) throws MemberNotBorrowingException{
        if(m == null) {
            System.out.println("the member doesnt exist!");
            return;
        }

        if(!BooksBorrowed.containsKey(m))
            throw new MemberNotBorrowingException("This member doesn't have borrowing record!");

        for(Book b : BooksBorrowed.get(m)){
            b.returnBook();
            m.returnBook();
            List<BorrowingRecord> thisRecord = getRecord(m, b);
            BorrowingRecord activeRecord = thisRecord.stream()
                                                    .filter(record -> record.getReturnDate() == null)
                                                    .findFirst()
                                                    .orElse(null);
            activeRecord.setReturnDate(LocalDateTime.now());
            System.out.println(m.getFull_name() + " has successfully returned " + b.getTitle()  + " , the available copies : " + b.getAvailable());
        }
        BooksBorrowed.get(m).clear();
    }  
    public void getAllBorrowingRecords(){
        if(allRecords.size() == 0) System.out.println("There are no Borrowing records yet!");
        else allRecords.forEach(System.out::println); 
    }
    public void getActiveBorrowingRecords(){
        List<BorrowingRecord> res = allRecords.stream()
                                              .filter(record -> record.getReturnDate() == null)
                                              .toList();

        if(res.size() == 0) System.out.println("There are no active records yet!");
        else res.forEach(System.out::println);    
    }
    public void getReturnedBorrowingRecords(){
        List<BorrowingRecord> res = allRecords.stream()
                                              .filter(record -> record.getReturnDate() != null).toList();

        if(res.size() == 0) System.out.println("There are no returned records yet!");
        else res.forEach(System.out::println);           
    }
    public List<BorrowingRecord> getAllRecordsForMember(Member m){
        if(m == null) return null;
        
        List<BorrowingRecord> res = allRecords.stream()
                                              .filter(record -> record.getMember() == m).toList();


        return res;
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
        int nb = 0; // check if no member has borrowed books
        for(Member m : BooksBorrowed.keySet()){
            if(BooksBorrowed.get(m).size() > 0){
                System.out.println(m);
                nb++;
            } 
        }
        if(nb == 0) System.out.println("No member has borrowed books");
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
