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
    public void viewHistory(){
        history.show();
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
            
            if(choice == 5){
                System.out.println("Saving data and exiting Smart Library System. Goodbye!");
                history.saveToFile();
                break;
            }
            handleChoice(choice, sc);
        }
        sc.close();
    }
    private void printMenu(){
        System.out.println("\n----- Smart Library -----");
        System.out.println("1.Add Book");
        System.out.println("2.Search");
        System.out.println("3.Borrow Book");
        System.out.println("4.History");
        System.out.println("5.Exit");
    }
    private void handleChoice(int choice, Scanner sc){
        switch(choice){
            case 1 -> {
                int isbn = 0;
                while(true){
                    try {
                        System.out.print("Enter ISBN: ");
                        isbn = sc.nextInt();
                        sc.nextLine();
                        break;
                    } catch(Exception e) {
                        System.out.println("Error: Invalid input. Try again");
                        sc.nextLine();
                    }
                }
                
                System.out.print("Enter Title: ");
                String title = sc.nextLine();
                System.out.print("Enter Author: ");
                String author = sc.nextLine();
                
                Book newBook = new Book(isbn, title, author);
                addBook(newBook);
            }
            case 2 -> {
                int searchIsbn = 0;
                while(true){
                    try {
                        System.out.print("Enter ISBN to search: ");
                        searchIsbn = sc.nextInt();
                        sc.nextLine();
                        break;
                    } catch(Exception e) {
                        System.out.println("Error: Invalid input. Try again");
                        sc.nextLine();
                    }
                }
                searchBook(searchIsbn);
            }
            case 3 -> {
                int borrowIsbn = 0;
                while(true){
                    try {
                        System.out.print("Enter ISBN to borrow: ");
                        borrowIsbn = sc.nextInt();
                        sc.nextLine();
                        break;
                    } catch(Exception e) {
                        System.out.println("Error: Invalid input. Try again");
                        sc.nextLine();
                    }
                }
                borrowBook(borrowIsbn);
            }
            case 4 -> viewHistory();
        }
    }
}