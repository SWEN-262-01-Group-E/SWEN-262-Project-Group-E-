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

    private static final String BOOKSFILE = "TextFiles/Books.txt";

    public static void main(String[] args) {

        //opens the library
        library = new OwningLibrary();

        library.openLibrary();

        HashMap<Long, Book> allBooks = new HashMap<Long, Book>();

       try{
           //creates the scanner to read the file and the suilder to create the book arguments
           Scanner reader = new Scanner(new File(BOOKSFILE));

           //iterate over every line
           while(reader.hasNextLine()){
               String next = reader.next();

               Book b = CreateBook(next);
               allBooks.put(b.getISBN(), b);
           }
       }catch (FileNotFoundException f){
           System.out.println("Could dont Find Books file");
       }

       /* if(args.length == 1) {
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
        }*/

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

    /**
     * A private helper method to parse a line from a given book file into a book object
     * @param line The line from the CSV file
     * @return A Book representation of the line being parsed
     */
    private static Book CreateBook(String line){

        //helpers to tell if we are parsing a title or author list so special characters are not ignored
        Boolean inTitle = false;
        Boolean inAuthors = false;

        StringBuilder argumentBuilder = new StringBuilder();

        int numParameters = 6;
        int currentParameter = 1;

        long ISBN = 0;
        String title = "";
        String authors = "";
        String publisher = "";
        String date = "";
        int totalPages = 1;

        for(int i = 0; i < line.length(); i++){
            char c = line.charAt(i);

            System.out.print(c);

            //if we are in the title or authors sections, append every character until notified to stop
            if(inTitle){
                if(c == '"'){
                    inAuthors = !inAuthors;
                }
                else {
                    argumentBuilder.append(c);
                }
            }
            else if(inAuthors){
                if(c == '}'){
                    inAuthors = !inAuthors;
                }
                else{
                    argumentBuilder.append(c);
                }
            }
            else{
                switch(c) {
                    //check for titles, so they will not be interupted
                    case('"'):
                        inTitle = !inTitle;
                        break;
                    //check for lists of suthors so they will not be interupted
                    case('{'):
                        inAuthors = !inAuthors;
                        break;
                    case('\n'):
                    case(','):
                        //end the current parameter, assign it to the correct value, and prepare to start again
                        switch (currentParameter){
                            case(1):
                                ISBN = Long.parseLong(argumentBuilder.toString().trim());
                                //reset the string builder after getting the value
                                argumentBuilder = new StringBuilder();
                                currentParameter++;
                                System.out.println(ISBN);
                                System.out.println(currentParameter);
                                break;
                            case(2):
                                title = argumentBuilder.toString();
                                //reset the string builder after getting the value
                                argumentBuilder = new StringBuilder();
                                currentParameter++;
                                System.out.println(title);
                                break;
                            case(3):
                                authors = argumentBuilder.toString();
                                //reset the string builder after getting the value
                                argumentBuilder = new StringBuilder();
                                break;
                            case(4):
                                publisher = argumentBuilder.toString().trim();
                                //reset the string builder after getting the value
                                argumentBuilder = new StringBuilder();
                                break;
                            case(5):
                                date = argumentBuilder.toString().trim();
                                //reset the string builder after getting the value
                                argumentBuilder = new StringBuilder();
                                break;
                            case(6):
                                totalPages = Integer.parseInt(argumentBuilder.toString().trim());
                                //reset the string builder after getting the value
                                argumentBuilder = new StringBuilder();
                                currentParameter++;
                                break;
                            default:

                            }//end switch 2
                            break;
                    default:
                        argumentBuilder.append(c);

                }//end switch 1

            }//end if-else

        }//end for loop

        return (new Book(ISBN, title, authors, publisher, date, totalPages, 1));

    }


    //test to ensure that system persistence works
/*
    private static void testPersistence(OwningLibrary library){
        Book book1 = new Book(123456, "Bacon", new Date(), 500, 3);
        Book book2 = new Book(789456, "Bits", new Date(), 400, 4);
        Book book3 = new Book(564231, "United", new Date(), 5, 1000);

        library.addBook(book1,1);
        library.addBook(book2,1);
        library.addBook(book3,1);

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
