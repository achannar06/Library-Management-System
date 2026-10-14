package Liberary;


public class Book implements Comparable<Book>{
    private final int id;
    private final String title; 
    private final String author;
    private final String publication_year;
    private final Category category;
    private int quantity;
    private int available;
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

    public void borrowBook() {
        if(available == 0) System.out.println("This book is unavailable");
        else
            this.available--;
    }

    public void returnBook(){
        this.available++;
    }

    @Override
    public String toString() {
        return "id=" + id + " : title=" + title + ", author=" + author + ", publication_year=" + publication_year
                + ", category=" + category + "]";
    }
    

    @Override
    public int compareTo(Book that) {
        return this.title.compareTo(that.title);
    }
}
