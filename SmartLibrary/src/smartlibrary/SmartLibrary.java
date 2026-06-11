/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package smartlibrary;

/**
 *
 * @author Nur Hasna Nadirah
 */
import java.util.Scanner;

public class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();
    
    public SmartLibrary() {
        history.loadFromFile();
        catalogue.loadFromFile();
    }
    @Override
    public void addBook(Book Book){
        catalogue.insert(Book.getIsbn(), Book.getTitle(), Book.getAuthor());
        System.out.println("\nSuccessfully added " + Book.getTitle() + " to the library catalogue.");
    }
    @Override
    public void searchBook(int isbn){
        System.out.println("\nSearching catalogue tree for ISBN " + isbn + "...");
        Book foundBook = catalogue.search(isbn);
       // i did chech for exception control here. exceptoon already implemented 
        if(foundBook != null){
            System.out.println("Result: [FOUND]");
            System.out.println("--------------------------------");
            System.out.println("ISBN   : " + foundBook.getIsbn());
            System.out.println("Title  : " + foundBook.getTitle());
            System.out.println("Author : " + foundBook.getAuthor());
            System.out.println("Status : " + (foundBook.isBorrowed() ? "Borrowed" : "Available"));
            System.out.println("--------------------------------");
        } else {
            System.out.println("ERROR: Book with ISBN not found");  
        }
    }
    public void searchBookByTitle(String title){
        System.out.println("\nSearching " + title + "...");
        Book foundBook = catalogue.searchByTitle(title);
       // i did chech for exception control here. exceptoon already implemented 
        if(foundBook != null){
            System.out.println("Result: [FOUND]");
            System.out.println("--------------------------------");
            System.out.println("ISBN   : " + foundBook.getIsbn());
            System.out.println("Title  : " + foundBook.getTitle());
            System.out.println("Author : " + foundBook.getAuthor());
            System.out.println("Status : " + (foundBook.isBorrowed() ? "Borrowed" : "Available"));
            System.out.println("--------------------------------");
        } else {
            System.out.println("ERROR: Book with title not found");  
        }
    }
    public void searchBookByAuthor(String author){
        System.out.println("\nSearching " + author + "...");
        Book foundBook = catalogue.searchByAuthor(author);
       // i did chech for exception control here. exceptoon already implemented 
        if(foundBook != null){
            System.out.println("Result: [FOUND]");
            System.out.println("--------------------------------");
            System.out.println("ISBN   : " + foundBook.getIsbn());
            System.out.println("Title  : " + foundBook.getTitle());
            System.out.println("Author : " + foundBook.getAuthor());
            System.out.println("Status : " + (foundBook.isBorrowed() ? "Borrowed" : "Available"));
            System.out.println("--------------------------------");
        } else {
            System.out.println("ERROR: Book with title not found");  
        }
    }
    @Override
    public void borrowBook(int isbn){
        System.out.println("\nChecking item availability for ISBN " + isbn + "...");
        Book targetBook = catalogue.search(isbn);
        
        if(targetBook != null){
            if(targetBook.isBorrowed()){
                System.out.println("Error: " + targetBook.getTitle() + " is already checked out.");
            } else {
                targetBook.setBorrowed(true);
                history.push(targetBook);
                System.out.println("Success: You have borrowed " + targetBook.getTitle());
            }
        } else {
            System.out.println("Error: Target ISBN does not exist in our reference system.");
        }
    }
    @Override
    public void returnBook(int isbn){
        System.out.println("\nChecking item for return...");
        Book targetBook = catalogue.search(isbn);
        if(targetBook != null){
            if(!targetBook.isBorrowed()){
                System.out.println("Error: " + targetBook.getTitle() + " is already available.");
            } else {
                targetBook.setBorrowed(false);
                System.out.println("Success: You have returned " + targetBook.getTitle());
            }
        } else {
            System.out.println("Error: Book not found.");
        }
    }
    public void viewHistory(){
        history.show();
    }
    public void viewAllBooks(){
        catalogue.displayAllBooks();
    }
    public void runMenu(){
        Scanner sc = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.print("Choice: ");
            
            if(!sc.hasNextInt()){
                System.out.println("Error: Invalid input");
                sc.next();
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();
            
            if(choice == 9){
                System.out.println("Saving data and exiting Smart Library System. Goodbye!");
                history.saveToFile();
                catalogue.saveToFile();
                break;
            }
            handleChoice(choice, sc);
        }
        sc.close();
    }
    private void printMenu(){
        System.out.println("\n----- Smart Library -----");
        System.out.println("1.Add Book");
        System.out.println("2.Search By ISBN");
        System.out.println("3.Search By Title");
        System.out.println("4.Search By Author");
        System.out.println("5.Borrow Book");
        System.out.println("6.Return Book");
        System.out.println("7.History");
        System.out.println("8.View All Book");
        System.out.println("9.Exit");
    }
    private void handleChoice(int choice, Scanner sc){
        switch(choice){
            case 1:
                System.out.print("Enter ISBN (Integer):");
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid ISBN format.");
                    sc.next();
                    System.out.print("Enter ISBN (Integer): ");
                }
                
                int isbn = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Title: ");
                String title = sc.nextLine();
                System.out.print("Enter Author: ");
                String author = sc.nextLine();
                
                Book newBook = new Book(isbn, title, author);
                addBook(newBook);
                break;
                
            case 2:
                System.out.print("Enter ISBN to search: ");
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid ISBN format.");
                    sc.next();
                    System.out.print("Enter ISBN to search: ");
                }
                
                int validIsbn = sc.nextInt();
                sc.nextLine();
                searchBook(validIsbn); 
                break;
                
            case 3:
                System.out.print("Enter book title to search: ");
                while (!sc.hasNextLine()) {
                    System.out.println("Invalid book title name.");
                    sc.next();
                    System.out.print("Enter book title to search: ");
                }
                
                String bookTitle = sc.nextLine();
                if(bookTitle.trim().isEmpty()){
                    System.out.println("Title cannot be empty.");
                    break;
                }
                searchBookByTitle(bookTitle);; 
                break;
                
            case 4:
                System.out.print("Enter book author to search: ");
                while (!sc.hasNextLine()) {
                    System.out.println("Invalid book author name.");
                    sc.next();
                    System.out.print("Enter book author to search: ");
                }
                
                String bookAuthor = sc.nextLine();
                if(bookAuthor.trim().isEmpty()){
                    System.out.println("Author cannot be empty.");
                    break;
                }
                searchBookByAuthor(bookAuthor);
                break;

            case 5:
                System.out.print("Enter ISBN to borrow: ");
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid ISBN format.");
                    sc.next();
                    System.out.print("Enter ISBN to borrow: ");
                }
                int borrowIsbn = sc.nextInt();
                sc.nextLine();
                borrowBook(borrowIsbn);
                break;
                
            case 6:
                System.out.print("Enter ISBN to return: ");
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid ISBN format.");
                    sc.next();
                    System.out.print("Enter ISBN to return: ");
                }
                int returnIsbn = sc.nextInt();
                sc.nextLine();
                returnBook(returnIsbn);
                break;
                
            case 7:
                viewHistory();
                break;
            
            case 8:
                viewAllBooks();
                break;

            default:
                System.out.println("Invalid option. Please choose 1-9.");
        }
    }
}