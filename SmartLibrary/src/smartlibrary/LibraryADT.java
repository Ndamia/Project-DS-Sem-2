/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package smartlibrary;

/**
 *
 * @author Nur Hasna Nadirah
 */
public interface LibraryADT {
    void addBook(Book Book);
    
    void searchBook(int isbn);
    
    void borrowBook(int isbn);
    
    void viewHistory();
}
