package Liberary;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Liberary lib = new Liberary();
        Scanner scanner = new Scanner(System.in);
        String exit = "";
        System.out.println("*".repeat(30));
        System.out.println("   Welcome to Your Liberary");
        System.out.println("*".repeat(30));
        System.out.println("/////////////////////////// MENU ///////////////////////////");
        System.out.println("////// Books Management");
        System.out.println("1. Add a book");
        System.out.println("2. Display all books");
        System.out.println("3. Search for a book by ID");
        System.out.println("4. Search for a book by title");
        System.out.println("5. Display available books");
        System.out.println("6. Filter books by category");
        System.out.println("7. Display books sorted alphabetically by title");
        System.out.println("////// Members Management");
        System.out.println("8. Register a new member");
        System.out.println("9. Search for a member by ID");
        System.out.println("10. Display all members");
        System.out.println("11. Display books borrowed by a member");
        System.out.println("////// Borrowing and returning");
        System.out.println("12. Borrow a book for a member");
        System.out.println("13. Return a book for a member");
        System.out.println("////// Reports and statistics");
        System.out.println("14. Display total number of book copies");
        System.out.println("15. Display total available copies");
        System.out.println("16. Display total currently borrowed copies");
        System.out.println("17. Display members who currently have borrowed books");
        System.out.println("18. Display the member with the most currently borrowed books");
        System.out.println("19. Exit");
        do{ 
            int choice = 0;
            do{
                System.out.print("Choose an option from 1 to 19 : ");
                try{
                    choice = scanner.nextInt();  
                }catch(InputMismatchException e){
                    System.out.println("Enter a valid integer Number");
                    choice = 0;
                }finally{
                    scanner.nextLine();
                }
            }while(choice < 1 || choice > 19);
            if(choice == 19) break;
            
            switch(choice){
                case 1 -> {
                    System.out.println("Adding a new book!");
                    System.out.println("*".repeat(30));
                    System.out.print("Enter the title : ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author : ");
                    String author = scanner.nextLine();
                    System.out.print("Enter the publication year : ");
                    String publication_year = scanner.nextLine();
                    
                    System.out.print("Enter the quantity : ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    boolean falseCategory = true;
                    Category category = null;
                    do{
                        try{
                            System.out.print("Enter the Category : ");
                            String c = scanner.nextLine();
                            category = Category.valueOf(c.toUpperCase());
                            falseCategory = false;
                        }catch(IllegalArgumentException e){
                            System.out.println("This category doesn't exist , enter a valid category!");
                        }catch(Exception e){
                            System.out.println("Something went wrong!");
                        }
                        
                    }while(falseCategory);
                    
                    lib.addBook(title, author, publication_year, category, quantity);
                }
                case 2 -> {
                    System.out.println("Displaying a new book!");
                    System.out.println("*".repeat(30));
                    lib.displayAllBooks();
                }
                case 3 -> {
                    System.out.println("Enter the book id : ");
                    int bookId = scanner.nextInt();
                    Book res = lib.getBookById(bookId);
                    if(res == null) System.out.println("This book doesn't exist");
                    else System.out.println(res);
                    scanner.nextLine();
                }
                case 4 -> {
                    System.out.println("Enter the book title : ");
                    String bookTitle = scanner.nextLine();
                    Book res = lib.getBookByTitle(bookTitle);
                    if(res == null) System.out.println("This book doesn't exist");
                    else System.out.println(res);
                }
                case 5 -> {
                    lib.displayAvailableBooks();
                }
                case 6 -> {
                    boolean falseCategory = true;
                    Category category = null;
                    do{
                        try{
                            System.out.print("Enter the Category : ");
                            String c = scanner.nextLine();
                            category = Category.valueOf(c.toUpperCase());
                            falseCategory = false;
                        }catch(IllegalArgumentException e){
                            System.out.println("This category doesn't exist , enter a valid category!");
                        }catch(Exception e){
                            System.out.println("Something went wrong!");
                        }
                        
                    }while(falseCategory);
                    List<Book> booksByCategory = lib.getBooksByCategory(category);
                    booksByCategory.forEach(System.out::println);
                }
                case 7 -> {
                    lib.displayBooksSorted();
                }
                case 8 -> {
                    System.out.print("Enter the full name of the member : ");
                    String full_name = scanner.nextLine();
                    System.out.print("Enter the email : ");
                    String email = scanner.nextLine();
                    System.out.print("Enter the phone_number : ");
                    String phone_number = scanner.nextLine();

                    lib.addMember(full_name, email, phone_number);
                    System.out.println(full_name + " added successfully!");
                }
                case 9 -> {
                    System.out.print("Enter the member id : ");
                    int memberID = scanner.nextInt();

                    Member res = lib.getMemberById(memberID);
                    if(res == null) System.out.println("The member with the id " + memberID + " Not found!");
                    else System.out.println(res);
                }
                case 10 -> {
                    lib.displayAllMembers();
                }
                case 11 -> {
                    System.out.println("Enter the member id : ");
                    int memberID = scanner.nextInt();
                    Member mb = lib.getMemberById(memberID);

                    if(mb == null) System.out.println("this member is not found!");
                    else lib.displayBooksBorrowedByMember(mb);
                }
                case 12 -> {
                    System.out.print("Enter the member id : ");
                    int memberID = scanner.nextInt();
                    System.out.print("Enter the Book id : ");
                    int bookID = scanner.nextInt();

                    Book book = lib.getBookById(bookID);
                    Member member = lib.getMemberById(memberID);

                    try {
                        lib.Borrow(member , book);
                    } catch (BookLimitExceededException | BookUnavailableException | AlreadyBorrowedException e) {
                        System.out.println(e);
                    }catch (Exception e){
                        System.out.println("Something went wrong");
                    }
                }
                case 13 -> {
                    System.out.print("Enter the member id : ");
                    int memberID = scanner.nextInt();
                    System.out.print("Enter the Book id : ");
                    int bookID = scanner.nextInt();

                    Book book = lib.getBookById(bookID);
                    Member member = lib.getMemberById(memberID);

                    try {
                        lib.returnB(member, book);
                    } catch (BookNotBorrowedException | MemberNotBorrowingException e) {
                        System.out.println(e);
                    } catch(Exception e){
                        System.out.println("Something went wrong!");
                    }
                }
                case 14 -> {
                    System.out.println("Total number of books (copies included) : " + lib.totalNumberOfBooks()); 
                }
                case 15 -> {
                    System.out.println("Total number of available books : " + lib.totalAvailableBooks()); 
                }
                case 16 -> {
                    System.out.println("Total number borrowed books : " + lib.totalBorrowedBooks()); 
                }
                case 17 -> {
                    System.out.println("Members who currently have borrowed books : ");
                    lib.MembersBorrowedBooks();
                }
                case 18 -> {
                    System.out.println("the member with the most currently borrowed books : ");
                    System.out.println(lib.mostBorrowing());
                }
            }

            System.out.print("Press anything to continue , press E to exit: ");
            exit = scanner.next();
        }while(!exit.equalsIgnoreCase("E"));

        System.out.println("Thank you for using the app! Bye");
        scanner.close();
    }
}