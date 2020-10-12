package main.Models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A model that represents a library using the LMS.
 * The library will keep track of all visitors in a hashmap using their ID
 * and all books using their ISBN
 * @author Alanna Morris
 */

public class OwningLibrary {

    private HashMap<Integer, LibraryEntry> Inventory = new HashMap<Integer, LibraryEntry> ();
    private HashMap<Integer, Visitor> Register = new HashMap<Integer, Visitor> ();

    /**
     * Creates a library with no books or visitors
     */
    public OwningLibrary() {
        //Nothing is needed here
    }

    public void addBook(Book book, int copies) {
        Integer ISBN = book.getISBN();
        LibraryEntry entry = new LibraryEntry(ISBN, copies);
        Inventory.put(ISBN, entry);
    }

    public void addVisitor(Visitor visitor) {
        Integer ID = visitor.getID();
        Register.put(ID, visitor);
    }

    public Boolean visitorCheckOut(Visitor visitor, Book book){
        if (Inventory.containsKey(book.getISBN())) {
            if (Inventory.get(book.getISBN()).canBeCheckedOut() && visitor.addCheckedOutBook(book)) {
                Inventory.get(book.getISBN()).checkoutBook();
                return true;
            }
        }
        return false;
    }

    public int visitorCheckIn(Visitor visitor, Book book) {
        int cost = -1;
        ArrayList<CheckedOut> allCheckouts = visitor.getCheckouts();
        for (CheckedOut checkedOut: allCheckouts) {
            if (checkedOut.getISBN() == book.getISBN()) {
                Inventory.get(book.getISBN()).checkinBook();
                cost = visitor.checkInBook(checkedOut);
            }
        }
        return cost;
    }

}
