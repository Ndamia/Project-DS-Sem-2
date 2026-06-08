/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartlibrary;

/**
 *
 * @author Nur Hasna Nadirah
 */
import java.util.Stack;
import java.io.Serializable;
import java.io.*;

public class BorrowStack implements Serializable {
    private Stack<Book> stack = new Stack<>();
    
    public void push (Book b){
        stack.push(b);
    }
    
    public void show(){
        if (stack.isEmpty()){
            System.out.println("History is empty.");
            return;
    }
        System.out.println("\n--- Borrowing History (Most Recent First) ---");
        //Corrected reverse iteration to show LIFO order properly
        for (int i = stack.size()-1 ; i >= 0; i-- ){
            Book b = stack.get(i);
            System.out.println("[ISBN : " + b.getIsbn() + "] " + b.getTitle() + " by " + b.getAuthor());
        }
    }
    
    public void saveToFile(){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("history.dat"))){
            os.writeObject(stack);
        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }
    
    public void loadFromFile(){
        File file = new File("history.dat");
        if(file.exists()){
            try(ObjectInputStream ios = new ObjectInputStream(new FileInputStream(file))){
               stack = (Stack<Book>) ios.readObject(); 
            } catch (Exception e) {
                System.out.println("Error loading history: " + e.getMessage());
            }
        }
    }
}
