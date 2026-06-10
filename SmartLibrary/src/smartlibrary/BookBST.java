package smartlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class BookBST {
    private Book root;

    public BookBST() {
        this.root = null;
    }

    public void insert(int isbn, String title, String author) {
        root = ins(root, isbn, title, author); 
    }


    private Book ins(Book r, int i, String t, String a) {
        if (r == null) { 
            return new Book(i, t, a); 
        }
        
        if (i < r.getIsbn()) {
            r.left = ins(r.left, i, t, a); 
        } else if (i > r.getIsbn()) {
            r.right = ins(r.right, i, t, a); 
        }
        
        return r;
    }
    
    public Book search(int isbn) {
        return sea(root, isbn); 
    }

    /**
     * Private helper utilizing strict recursion.
     * Traverses the tree with O(log n) efficiency.
     */
    private Book sea(Book r, int isbn) {
        // BASE CASE 1 & 2: If node is empty (not found) or matches target ISBN
        if (r == null || r.getIsbn() == isbn) {
            return r;
        }
        
        // RECURSIVE STEP: Decide whether to move left or right down the tree
        if (isbn < r.getIsbn()) {
            return sea(r.left, isbn); // Strict recursive call to left child
        } else {
            return sea(r.right, isbn); // Strict recursive call to right child
        }
    }
    
    public void searchByTitle(String title){
        searchTitle(root, title);
    }
    private void searchTitle(Book node, String title){
        if(node != null){
            searchTitle(node.left, title);
            if(node.getTitle().equalsIgnoreCase(title)){
                System.out.println(node);
            }
            searchTitle(node.right, title);
        }
    }
    
    public void searchByAuthor(String author){
        searchAuthor(root, author);
    }
    private void searchAuthor(Book node, String author){
        if(node != null){
            searchAuthor(node.left, author);
            if(node.getAuthor().equalsIgnoreCase(author)){
                System.out.println(node);
            }
            searchAuthor(node.right, author);
        }
    }
    
    
    private void inorder(Book node){
        if(node != null){
            inorder(node.left);
            System.out.println("ISBN: " + node.getIsbn());
            System.out.println("Title: " + node.getTitle());
            System.out.println("Author: " + node.getAuthor());
            System.out.println("Status: " + (node.isBorrowed() ? "Borrowed" : "Available"));
            System.out.println();
            
            inorder(node.right);
        }
    }
    
     public void saveToFile(){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("catalogue.dat"))){
            os.writeObject(root);
        } catch (IOException e) {
            System.out.println("Error saving catalogue: " + e.getMessage());
        }
    }
    
    public void loadFromFile(){
        File file = new File("catalogue.dat");
        if(file.exists()){
            try(ObjectInputStream ios = new ObjectInputStream(new FileInputStream(file))){
               root = (Book) ios.readObject(); 
            } catch (Exception e) {
                System.out.println("Error loading catalogue: " + e.getMessage());
            }
        }
    }
}