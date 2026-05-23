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
    public SmartLibrary() {
        
    }
    public void addBook(int isbn, String title, String author){
        System.out.println("will add later");
    }
    public void searchBook(int isbn){
        System.out.println("will add later");
    }
    public void borrowBook(int isbn){
        System.out.println("will add later");
    }
    public void viewLatestHistory(){
        System.out.println("will add later");
    }
    public void runMenu(){
        Scanner sc = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.println("Choice: ");
            
            if(!sc.hasNextInt()){
                System.out.println("Error: Invalid input");
                sc.next();
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();
            
            if(choice == 5){
                System.out.println("Exiting Smart Library System. Goodbye!");
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
                System.out.print("Enter ISBN: ");
                while(!sc.hasNextInt()){
                    System.out.println("Error. Try again");
                    sc.next();
                }
                int isbn = sc.nextInt();
                sc.nextLine();
                
                System.out.print("Enter Title: ");
                String title = sc.nextLine();
                System.out.print("Enter Author: ");
                String author = sc.nextLine();
                addBook(isbn, title, author);
            }
            case 2 -> {
                System.out.print("Enter ISBN to search: ");
                while(!sc.hasNextInt()){
                    System.out.println("Error. Try again");
                    sc.next();
                }
                int searchIsbn = sc.nextInt();
                searchBook(searchIsbn);
            }
            case 3 -> {
                System.out.print("Enter ISBN to borrow: ");
                while(!sc.hasNextInt()){
                    System.out.println("Error. Try again");
                    sc.next();
                }
                int borrowIsbn = sc.nextInt();
                borrowBook(borrowIsbn);
            }
            case 4 -> viewLatestHistory();
        }
    }
}
