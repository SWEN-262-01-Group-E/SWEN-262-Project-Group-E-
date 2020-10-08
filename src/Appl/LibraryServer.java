package Appl;

import main.Models.Book;
import main.Models.OwningLibrary;
import main.Models.Visitor;

import java.util.Date;

public class LibraryServer {

    private static OwningLibrary library;

    public static void main(String[] args) {

        library = new OwningLibrary();

        //used to test that the system worked
        //testPersistence(library);

    }


    /*

    test to ensure that system persistence works

    private static void testPersistence(OwningLibrary library){
        Book book1 = new Book(123456, "Bacon", new Date(), 500, 3);
        Book book2 = new Book(789456, "Bits", new Date(), 400, 4);
        Book book3 = new Book(564231, "United", new Date(), 5, 1000);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        //String firstName, String lastName, String address, int phoneNumber, int ID, ArrayList<Book> booksCheckedOut

        Visitor v1 = new Visitor("Sherlock", "Holms", "221B Baker Street", 1234567891, 1);
        Visitor v2 = new Visitor("Howard", "Lovecraft", "454 Angell Street", 999999999, 2);
        Visitor v3 = new Visitor("Dory", "Just Dory", "P. Sherman 42 Wallaby Way", 1131131131, 3);

        library.addVisitor(v1);
        library.addVisitor(v2);
        library.addVisitor(v3);

        library.closeLibrary();


        System.out.println("==============================================================================");
        System.out.println("==============================================================================");
        System.out.println("==============================================================================");

        OwningLibrary testLibrary = new OwningLibrary();

        testLibrary.openLibrary();

        System.out.println(testLibrary.toString());
    }*/

}
