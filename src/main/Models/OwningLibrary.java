package main.Models;

import java.io.*;
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
    }

    /**
     * saves the visitors and Books to an external file
     */
    public void openLibrary(){

        readVisitors();
        readBooks();
    }

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
                    Book book = (Book) oBook.readObject();
                    this.Inventory.put(book.getISBN(), book);
                   // oBook = new ObjectInputStream(fBook);
                }
            }catch (EOFException ignored){
            }

            fBook.close();
            oBook.close();

        }catch (FileNotFoundException f) {
            System.out.println("BookLog file not found");
        }catch(IOException i){
            System.out.println("Error initializing stream");
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
            System.out.println("BookLog file not found");
        }catch(IOException i){
            System.out.println("Error initializing stream");
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
                Book b = (Book) pair.getValue();

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
}
