package Appl;

import main.Models.Book;
import main.Models.OwningLibrary;
import main.Models.Visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;
import java.util.Scanner;

public class LibraryServer {

    private static OwningLibrary library;

    public static void main(String[] args) {

        HashMap<Integer, Book> allBooks = new HashMap<Integer, Book>();

        //opens the library
        library = new OwningLibrary();

        library.openLibrary();

        if(args.length == 1) {
            try {
                Scanner reader = new Scanner(new File(args[0]));
                while(reader.hasNextLine())
                {
                    ArrayList<String> arguments = splitCSV(reader.nextLine());

                    Book b = new Book(Integer.parseInt(arguments.get(0)),arguments.get(3),
                            new Date(arguments.get(4)), 0, 1);

                    allBooks.put(b.getISBN(), b);
                }
            }
            catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }

        StringBuilder commandBuilder = new StringBuilder();
        Scanner commandScanner = new Scanner(System.in);
        Boolean isRunning = true;
        do {
            do {
                System.out.print("Enter commands >");
                commandBuilder.append(commandScanner.nextLine());
            } while(!commandBuilder.toString().endsWith(";"));
            for(String command : commandBuilder.toString().split(";")) {

                System.out.println(commandBuilder.toString());
                if(command.equals("quit")) {
                    isRunning = false;
                    break;
                }
                else if(command.length() > 0) {

                    ArrayList<String> arguments = splitCSV(command);

                    switch(arguments.get(0)) {
                        default:
                            break;
                        case "command":
                            //Check arguments and call method method for that command
                            //Example:
                            /*
                                if(arguments.size() == 3)
                                    command_method(arguments.get(1), arguments.get(2))
                            */
                            //Could also parse integers and check those
                            break;
                    }
                }

            }
        } while(isRunning);

        //Save Library
        //End Application
        library.closeLibrary();

        System.out.println("IT WORKS");
        //used to test that the system worked
        //testPersistence(library);

    }

    private static ArrayList<String> splitCSV(String masterString) {
        ArrayList<String> arguments = new ArrayList<String>();

        //This could include matches with curly brackets within curly brackets. Filter them out and add them
        Pattern curlyBrackets = Pattern.compile("\\{(.*?)},");
        Matcher matcher = curlyBrackets.matcher(masterString);
        while(matcher.find()) {
            String s = matcher.group();
            String substring = s.substring(1, s.length() - 3);

            if(substring.contains("{") || substring.contains("}")) continue;

            arguments.add(substring);
            masterString = masterString.replace(s, "");
        }

        //Add remaining arguments
        for(String s : masterString.split(",")) {
            arguments.add(s);
        }

        return arguments;
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
