/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smartlibrary;
import java.io.Serializable;
/**
 *
 * @author Nur Hasna Nadirah
 */
public class Book implements Serializable {
    private int isbn;
    private String title;
    private String author;
    private boolean borrowed;
    
    public Book left;
    public Book right;
    
    public Book(int isbn, String title, String author){
        
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.borrowed = false;
        this.left = null;
        this.right = null;
    }
    
    public int getIsbn(){
        return isbn;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public boolean isBorrowed(){
        return borrowed;
    }
    
    public void setBorrowed(boolean borrowed){
        this.borrowed = borrowed;
    }
    
    @Override
    public String toString(){
        return "ISBN: "+ isbn +"\nTitle: "+ title +"\nAuthor: "+ author +"\nBorrowed: "+ borrowed ;
    }
}
