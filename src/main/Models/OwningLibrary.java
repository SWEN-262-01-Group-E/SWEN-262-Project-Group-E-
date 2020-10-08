package main.Models;

import java.util.HashMap;
//TODO Make Book an entry class, so that # of copies and # of copies checked out are together

/**
 * A model that represents a library using the LMS.
 * The library will keep track of all visitors in a hashmap using their ID
 * and all books using their ISBN
 * @author Alanna Morris
 */

public class OwningLibrary {

    private HashMap<Integer, Book> Inventory = new HashMap<Integer, Book> ();
    private HashMap<Integer, Visitor> Register = new HashMap<Integer, Visitor> ();

    /**
     * Creates a library with no books or visitors
     */
    public OwningLibrary() {
        //Nothing is needed here
    }

    public void addBook(Book book) {
        Integer ISBN = book.getISBN();
        Inventory.put(ISBN, book);
    }

    public void addVisitor(Visitor visitor) {
        Integer ID = visitor.getID();
        Register.put(ID, visitor);
    }
}
