package Liberary;


public class Book implements Comparable<Book>{
    private final int id;
    private String title; 
    private String author;
    private String publication_year;
    private Category category;
    private int quantity;
    private int available;
    private boolean isDeleted;
    private static int count = 1;
    
    private static synchronized int generateId(){
        return count++;
    }

    public Book(String title, String author, String publication_year, Category category , int quantity) {
        this.id = generateId();
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
        this.category = category;
        this.quantity = quantity;
        available = quantity;
        isDeleted = false;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublication_year() {
        return publication_year;
    }
    public Category getCategory() {
        return category;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getAvailable() {
        return available;
    }
    public boolean getIsDeleted(){
        return isDeleted;
    }
    public int getBorrowedCopies(){
        return quantity - available;
    }
    
    
    
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setQuantity(int quantity) {
        this.available =  quantity - getBorrowedCopies();
        this.quantity = quantity;
    }
    
    
    public void borrowBook() {
        if(available == 0) System.out.println("This book is unavailable");
        else
            this.available--;
    }
    public void returnBook(){
        this.available++;
    }
    public void deleteBook(){
        isDeleted = true;
    }
    public void restorBook(){
        isDeleted = false;
    }
    

    @Override
    public String toString() {
        return "id=" + id + " : title=" + title + ", author=" + author + ", publication_year=" + publication_year
                + ", category=" + category + ", quantity=" + quantity + ", available=" + available;
    }
    

    @Override
    public int compareTo(Book that) {
        return this.title.compareTo(that.title);
    }
}
