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
}