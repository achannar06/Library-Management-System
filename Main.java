package Liberary;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    static Liberary lib = new Liberary();

    public static int getChoiceFomUserInput(int start , int end){
        int choice = -1;
        do{
            System.out.print("Choose an option from " + start + " to " + end + " : ");
            try{
                choice = scanner.nextInt();  
            }catch(InputMismatchException e){
                System.out.println("Enter a valid integer Number");
            }finally{
                scanner.nextLine();
            }
        }while(choice < start || choice > end);

        return choice;
    }
    public static int getValidIntegerFromUserInput(String typeOfInput){
        int validPositiveInteger = 0;
        do{
            try{
                System.out.print("Enter the " + typeOfInput + " (can't be 0 or negative) : ");
                validPositiveInteger = scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Enter a valid Integer Number");
            }finally{
                scanner.nextLine();
            }
        }while(validPositiveInteger <= 0);

        return validPositiveInteger;
    }
    public static Category getCategoryFromUserInput(){
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
            }
            
        }while(falseCategory);

        return category;
    }
    


    public static void main(String[] args){
        System.out.println("*".repeat(30));
        System.out.println("   Welcome to Your Liberary");
        System.out.println("*".repeat(30));
        do{
            System.out.println("/////////////////////////// MENU ///////////////////////////");
            System.out.println("////// Books Management");
            System.out.println("1. Add a book");
            System.out.println("2. Update a book");
            System.out.println("3. Delete a book");
            System.out.println("4. Restore a book");
            System.out.println("5. Display all books");
            System.out.println("6. Search for a book by ID");
            System.out.println("7. Search for a book by title");
            System.out.println("8. Display available books");
            System.out.println("9. Filter books by category");
            System.out.println("10. Display books sorted alphabetically by title");

            System.out.println("////// Members Management");
            System.out.println("11. Register a new member");
            System.out.println("12. Update a member");
            System.out.println("13. Delete a member");
            System.out.println("14. Restore a member");
            System.out.println("15. Search for a member by ID");
            System.out.println("16. Display all members");
            System.out.println("17. Display books borrowed by a member");

            System.out.println("////// Borrowing and returning");
            System.out.println("18. Borrow a book for a member");
            System.out.println("19. Return a book for a member");
            System.out.println("20. Return All books for a member");
            System.out.println("////// Reports and statistics");
            System.out.println("21. Display total number of book copies");
            System.out.println("22. Display total available copies");
            System.out.println("23. Display total currently borrowed copies");
            System.out.println("24. Display members who currently have borrowed books");
            System.out.println("25. Display the member with the most currently borrowed books");
            System.out.println("26. Display all Borrowing Records");
            System.out.println("27. Display Active Borrowing Records");
            System.out.println("28. Display Returned Borrowing Records");
            System.out.println("29. Display All records by a specific member and book");
            System.out.println("30. Display All borrowing history for a member");

            System.out.println("31. Exit");
            int choice = getChoiceFomUserInput(1 , 31);
            if(choice == 31) break;
            
            switch(choice){
                case 1 -> {
                    do{
                        System.out.print("Enter the title : ");
                        String title = scanner.nextLine();
                        System.out.print("Enter the author : ");
                        String author = scanner.nextLine();
                        System.out.print("Enter the publication year : ");
                        String publication_year = scanner.nextLine();
                        Category category = getCategoryFromUserInput();
                        int quantity = getValidIntegerFromUserInput("quantity");
                        
                        lib.addBook(title, author, publication_year, category, quantity);
                        System.out.println("The book " + title + " has been added successfully!");

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 2 -> {
                    do{
                        Book book = lib.getBookById(getValidIntegerFromUserInput("book id"));
                        if(book == null){
                            System.out.println("This book doesn't exist!");
                        }else{

                            System.out.println("Avaiblabe updating options :");
                            System.out.println("1. Update title");
                            System.out.println("2. Update author");
                            System.out.println("3. Update publication year");
                            System.out.println("4. Update category");
                            System.out.println("5. Update quantity");

                            int option = getChoiceFomUserInput(1, 5);
                        
                            switch(option){
                            case 1 -> {
                                System.out.print("Enter the Updated Title : ");
                                String title = scanner.nextLine();
                                book.setTitle(title);
                                System.out.println("Title has been successfully updated!");
                            }
                            case 2 -> {
                                System.out.print("Enter the Updated Author : ");
                                String author = scanner.nextLine();
                                book.setAuthor(author);
                                System.out.println("Author has been successfully updated!");
                            }
                            case 3 -> {
                                System.out.print("Enter the new publication year : ");
                                String pub_year = scanner.nextLine();
                                book.setPublication_year(pub_year);
                                System.out.println("Publication year has been successfully updated!");
                            }
                            case 4 -> {
                                book.setCategory(getCategoryFromUserInput());
                                System.out.println("Category has been successfully updated!");
                            }
                            case 5 -> {
                                int newQuantity = getValidIntegerFromUserInput("quantity");
                                if(newQuantity >= book.getBorrowedCopies()){
                                    book.setQuantity(newQuantity);
                                    System.out.println("Quantity has been successfully updated!");
                                }
                                else{
                                    System.out.println("ERROR ! Your new Quantity is less than the number of borrowed copies!");
                                }
                            }
                        }
                        }
                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option1 = getChoiceFomUserInput(0 , 1); 
                        if(option1 == 0) break;
                    }while(true);
                }
                case 3 -> {
                    do{
                        Book book = lib.getBookById(getValidIntegerFromUserInput("book id"));
                        if(book == null){
                            System.out.println("This book doesn't exit!");
                            
                        }
                        else if(book.getBorrowedCopies() > 0){
                            System.out.println("This book is still on loan you can't delete it");
                        }else{
                            book.deleteBook();
                            System.out.println("This book is successfuly deleted!");
                        }

                        
                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 4 -> {
                    do{
                        Book book = lib.getDeletedBookById(getValidIntegerFromUserInput("deleted book id"));
                        if(book == null){
                            System.out.println("There is no deleted book with this id");
                        }else{
                            book.restorBook();
                            System.out.println("This book has been successfuly restored!");
                        }

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 5 -> {
                    System.out.println("All Books :");
                    lib.displayAllBooks();
                }
                case 6 -> {
                    do{
                        Book book = lib.getBookById(getValidIntegerFromUserInput("book id"));
                        if(book == null) System.out.println("This book doesn't exist");
                        else System.out.println(book);

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 7 -> {
                    do{
                        System.out.print("Enter the book title : ");
                        String bookTitle = scanner.nextLine();
                        Book res = lib.getBookByTitle(bookTitle);
                        if(res == null) System.out.println("This book doesn't exist");
                        else System.out.println(res);

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 8 -> {
                    System.out.println("All Available Books: ");
                    lib.displayAvailableBooks();
                }
                case 9 -> {
                    do{
                        Category category = getCategoryFromUserInput();
                        List<Book> booksByCategory = lib.getBooksByCategory(category);
                        if(booksByCategory.size() > 0){
                            System.out.println("All books with the category " + category + " : ");
                            booksByCategory.forEach(System.out::println);
                        }else System.out.println("There are no books with this category!");

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 10 -> {
                    System.out.println("All Books sorted alphabitically by the title");
                    lib.displayBooksSorted();
                }
                case 11 -> {
                    do{
                        System.out.print("Enter the full name of the member : ");
                        String full_name = scanner.nextLine();
                        System.out.print("Enter the email : ");
                        String email = scanner.nextLine();
                        System.out.print("Enter the phone_number : ");
                        String phone_number = scanner.nextLine();

                        lib.addMember(full_name, email, phone_number);
                        System.out.println(full_name + " added successfully!");

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 12 -> {
                    do{
                        Member mb = lib.getMemberById(getValidIntegerFromUserInput("member id"));
                        if(mb == null) {
                            System.out.println("This member doesn't exist"); 
                        }else{
                            System.out.println("Avaiblabe updating options :");
                            System.out.println("1. Update full name");
                            System.out.println("2. Update email");
                            System.out.println("3. Update phone number");

                            int option = getChoiceFomUserInput(1, 3);
                            switch(option){
                            case 1 -> {
                                System.out.print("Enter the new full name: ");
                                String full_name = scanner.nextLine();
                                mb.setFull_name(full_name);
                                System.out.println("The full name has been successfully updated");
                            }
                            case 2 -> {
                                System.out.print("Enter the new email: ");
                                String email = scanner.nextLine();
                                mb.setEmail(email);
                                System.out.println("The email has been successfully updated");
                            }
                            case 3 -> {
                                System.out.print("Enter the new phone number: ");
                                String phone_number = scanner.nextLine();
                                mb.setPhone_number(phone_number);
                                System.out.println("The phone number has been successfully updated");
                            }
                        }
                        }
                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option2 = getChoiceFomUserInput(0 , 1); 
                        if(option2 == 0) break;
                    }while(true);
                }
                case 13 -> {
                    do{
                        Member mb = lib.getMemberById(getValidIntegerFromUserInput("member id"));
                        if(mb == null) {
                            System.out.println("This member doesn't exist");
                        }
                        else if(mb.getBorrowsLeft() < 3){
                            System.out.println("This member still has active borrowings , you can't delete them");
                        }else{
                            mb.deleteMember();
                            System.out.println("This member has been successfuly deleted");
                        }

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 14 -> {
                    do{
                        Member mb = lib.getDeletedMemberById(getValidIntegerFromUserInput("deleted member id"));
                        if(mb == null) {
                            System.out.println("There is no deleted member with this id");
                        }else {
                            mb.restorMember();
                            System.out.println("This member has been successfuly restored");
                        }

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 15 -> {
                    do{
                        int id = getValidIntegerFromUserInput("member id");               
                        Member res = lib.getMemberById(id);
                        if(res == null) System.out.println("The member with the id " + id + " Not found!");
                        else System.out.println(res);

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 16 -> {
                    System.out.println("All Members: ");
                    lib.displayAllMembers();
                }
                case 17 -> {
                    do{
                        Member mb = lib.getMemberById(getValidIntegerFromUserInput("member id"));

                        if(mb == null) System.out.println("this member is not found!");
                        else lib.displayBooksBorrowedByMember(mb);
                        
                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 18 -> {  
                    do{
                        Member member = lib.getMemberById(getValidIntegerFromUserInput("member id"));
                        Book book = lib.getBookById(getValidIntegerFromUserInput("book id"));

                        try {
                            lib.Borrow(member , book);
                        } catch (BookLimitExceededException | BookUnavailableException | AlreadyBorrowedException e) {
                            System.out.println(e);
                        }catch (Exception e){
                            System.out.println("Something went wrong");
                        }
                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);  
                }
                case 19 -> {  
                    do{ 
                        Member member = lib.getMemberById(getValidIntegerFromUserInput("member id"));
                        Book book = lib.getBookById(getValidIntegerFromUserInput("book id"));

                        try {
                            lib.returnB(member, book);
                        } catch (BookNotBorrowedException | MemberNotBorrowingException e) {
                            System.out.println(e);
                        } catch(Exception e){
                            System.out.println("Something went wrong!");
                        }
                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 20 -> {
                    do{
                         Member member = lib.getMemberById(getValidIntegerFromUserInput("member id"));

                        try {
                            lib.returnAll(member);
                        } catch (MemberNotBorrowingException e) {
                            System.out.println(e);
                        } catch(Exception e){
                            System.out.println("Something went wrong!");
                        }
                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 21 -> {
                    System.out.println("Total number of books (copies included) : " + lib.totalNumberOfBooks()); 
                }
                case 22 -> {
                    System.out.println("Total number of available books : " + lib.totalAvailableBooks()); 
                }
                case 23 -> {
                    System.out.println("Total number borrowed books : " + lib.totalBorrowedBooks()); 
                }
                case 24 -> {
                    System.out.println("Members who currently have borrowed books : ");
                    lib.MembersBorrowedBooks();
                }
                case 25 -> {
                    Member m = lib.mostBorrowing();
                    if(m == null) System.out.println("No member have borrowings currently");
                    else {
                        System.out.println("the member with the most currently borrowed books : ");
                        System.out.println();
                    }
                }
                case 26 -> {
                    System.out.println("All Borrowing Records : ");
                    lib.getAllBorrowingRecords();
                }
                case 27 -> {
                    System.out.println("Active Borrowing Records : ");
                    lib.getActiveBorrowingRecords();
                }
                case 28 -> {
                    System.out.println("Returned Borrowing Records : ");
                    lib.getReturnedBorrowingRecords();
                }
                case 29 -> {
                    do{
                        Book book = lib.getBookById(getValidIntegerFromUserInput("book"));
                        Member member = lib.getMemberById(getValidIntegerFromUserInput("member"));

                        List<BorrowingRecord> res = lib.getRecord(member, book);
                        if(res == null) System.out.println("The member or the Book doesn't exist");
                        else if(res.size() == 0) System.out.println("This member has never borrowed this book");
                        else res.forEach(System.out::println);

                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
                case 30 -> { 
                    do{
                        Member member = lib.getMemberById(getValidIntegerFromUserInput("member id"));

                        List<BorrowingRecord> res = lib.getAllRecordsForMember(member);
                        if(res == null) System.out.println("This member doesn't exist");
                        else if(res.size() == 0) System.out.println("This member has no borrowing records");
                        else res.forEach(System.out::println);
                        System.out.println("Continue or Exit this operation? choose 1 to continue , 0 to exit");
                        int option = getChoiceFomUserInput(0 , 1); 
                        if(option == 0) break;
                    }while(true);
                }
            
            }
        }while(true);

        System.out.println("Thank you for using the app! Bye");
        scanner.close();
    }
}