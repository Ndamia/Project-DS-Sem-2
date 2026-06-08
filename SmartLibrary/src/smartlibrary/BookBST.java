package smartlibrary;

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
}