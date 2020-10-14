package main.Models;

import java.io.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A model that represents a library using the LMS.
 * The library will keep track of all visitors in a hashmap using their ID
 * and all books using their ISBN
 * @author Alanna Morris
 * @author Joseph Saltalamacchia
 */

public class OwningLibrary {

    private HashMap<Long, LibraryEntry> Inventory = new HashMap<Long, LibraryEntry> ();
    private HashMap<Long, Visitor> Register = new HashMap<Long, Visitor> ();

    private TimeManager time;
    /**
     * Creates a library with no books or visitors
     */
    public OwningLibrary() {
        openLibrary();
    }

    public void addBook(Book book, int copies) {
        LibraryEntry entry = new LibraryEntry(book, copies);
        Inventory.put(book.getISBN(), entry);
    }

    public void addVisitor(Visitor visitor) {
        Long ID = visitor.getID();
        Register.put(ID, visitor);
    }

    public HashMap<Long, Visitor> getRegister() {
        return Register;
    }

    public HashMap<Long, LibraryEntry> getInventory() {
        return Inventory;
    }

    @Override
    public String toString() {
        return "OwningLibrary{" +
                "Inventory=" + Inventory.toString() +
                ", Register=" + Register.toString() +
                '}';
    }

    /**
     * saves the visitors and Books to an external file
     */
    public void closeLibrary(){

        writeVisitors();
        writeBooks();
        writeTime();
    }

    /**
     * saves the visitors and Books to an external file
     */
    public void openLibrary(){

        readVisitors();
        readBooks();
        readTime();
    }

    /**
     * Allows a given visitor to check out a book
     * @param visitor the visitor checking out the book
     * @param ISBN the ISBN of the book being checked out
     * @return if the visitor successfully checked out the book or not
     */
    public Boolean visitorCheckOut(Visitor visitor, Long ISBN){
        if (Inventory.containsKey(ISBN)) {
            //retrive the book objject that the Library entry is wrapping
            Book book = Inventory.get(ISBN).getBook();
            if (Inventory.get(ISBN).canBeCheckedOut() && visitor.addCheckedOutBook(book)) {
                Inventory.get(ISBN).checkoutBook();
                return true;
            }
        }
        return false;
    }





    //=====================================================================================================================
    //==================================================Readers and Writers================================================
    //=====================================================================================================================


    /**
     * A private helper method to read all of the saved Books from the file they were saved on
     */
    private void readBooks(){
        try{
            FileInputStream fBook = new FileInputStream(new File("TextFiles/BookLog.bin"));
            ObjectInputStream oBook = new ObjectInputStream(fBook);

            boolean keepReading = true;
            try{
                while(keepReading){
                    LibraryEntry book = (LibraryEntry) oBook.readObject();
                    this.Inventory.put(book.getISBN(), book);
                }
            }catch (EOFException ignored){
            }

            fBook.close();
            oBook.close();

        }catch (FileNotFoundException f) {
            System.out.println("BookLog file not found");
        }catch(IOException i){
            System.out.println("No Books In library");
        }catch (ClassNotFoundException c){
            System.out.println("could not find class");
        }
    }


    /**
     * A private helper method to read all of the saved Visitors from the file they were saved on
     */
    private void readVisitors(){
        try{
            FileInputStream fVisitor = new FileInputStream(new File("TextFiles/VisitorLog.bin"));
            ObjectInputStream oVisitor = new ObjectInputStream(fVisitor);

            boolean keepReading = true;
            try{
                while(keepReading){
                    Visitor visitor = (Visitor) oVisitor.readObject();
                    this.Register.put(visitor.getID(), visitor);
                    // oVisitor = new ObjectInputStream(fVisitor);
                }
            }catch (EOFException ignored){
            }

            fVisitor.close();
            oVisitor.close();

        }catch (FileNotFoundException f) {
            System.out.println("VisitorLog file not found");
        }catch(IOException i){
            System.out.println("No Visitors registered in library");
        }catch (ClassNotFoundException c){
            System.out.println("could not find class");
        }
    }

    /**
     * private helper method to print Visitors out to a file
     */
    private void writeBooks(){
        try{
            //create a writer for the Books
            FileOutputStream fBook = new FileOutputStream(new File("TextFiles/BookLog.bin"));
            ObjectOutputStream oBook = new ObjectOutputStream(fBook);

            Iterator BookIterator = Inventory.entrySet().iterator();

            //print each visitor to the file
            while(BookIterator.hasNext()){
                Map.Entry pair = (Map.Entry) BookIterator.next();
                LibraryEntry b = (LibraryEntry) pair.getValue();

                oBook.writeObject(b);
                System.out.println(b.toString());
                BookIterator.remove();
            }

            fBook.close();
            oBook.close();

        }catch(FileNotFoundException f){
            System.out.println("Book File Not Found");
        } catch(IOException i){
            System.out.println("Error initializing stream");
        }
    }

    /**
     * private helper method to print Visitors out to a file
     */
    private void writeVisitors(){
        try{
            //create a writer for the visitors
            FileOutputStream fVisitor = new FileOutputStream(new File("TextFiles/VisitorLog.bin"));
            ObjectOutputStream oVisitor = new ObjectOutputStream(fVisitor);

            Iterator VisitorIterator = Register.entrySet().iterator();

            //print each visitor to the file
            while(VisitorIterator.hasNext()){
                Map.Entry pair = (Map.Entry) VisitorIterator.next();
                Visitor v = (Visitor) pair.getValue();

                oVisitor.writeObject(v);

                System.out.println(v.toString());
                VisitorIterator.remove();
            }

        }catch(FileNotFoundException f){
            System.out.println("Visitor File Not Found");
        } catch(IOException i){
            System.out.println("Error initializing stream");
        }
    }

    private void readTime() {
        try{
            FileInputStream fTime = new FileInputStream(new File("TextFiles/TimeLog.bin"));
            ObjectInputStream oTime = new ObjectInputStream(fTime);

            try{
                //Use file to create TimeManager
                time = (TimeManager)oTime.readObject();
            }catch (EOFException ignored){
            }

            fTime.close();
            oTime.close();

        }catch (FileNotFoundException f) {
            //if no file, create a new TimeManager
            time = new TimeManager();
        }catch(IOException i){
            System.out.println("Error initializing stream");
        }catch (ClassNotFoundException c){
            System.out.println("could not find class");
        }
    }
    private void writeTime() {
        try{
            //create a writer for the visitors
            FileOutputStream fTime = new FileOutputStream(new File("TextFiles/TimeLog.bin"));
            ObjectOutputStream oTime = new ObjectOutputStream(fTime);

            //writes the time object into the file
            oTime.writeObject(time);

        }catch(FileNotFoundException f){
            System.out.println("Time File Not Found");
        } catch(IOException i){
            System.out.println("Error initializing stream");
        }
    }



    /*public int visitorCheckIn(Visitor visitor, Book book) {
        int cost = -1;
        ArrayList<CheckedOut> allCheckouts = visitor.getCheckouts();
        for (CheckedOut checkedOut: allCheckouts) {
            if (checkedOut.getISBN() == book.getISBN()) {
                Inventory.get(book.getISBN()).checkinBook();
                cost = visitor.checkInBook(checkedOut);
            }
        }
        return cost;
    }*/

}
