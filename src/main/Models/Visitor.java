package main.Models;


import main.Models.StrategyCostCalc.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A model the represents a visitor to a library
 *
 * @author Joseph Saltalamacchia
 */
public class Visitor {

    private String firstName;
    private String lastName;
    private String address;
    private int phoneNumber;  //TODO:do we want this to be a string or an integer?
    private int ID;
    private ArrayList<CheckedOut> booksCheckedOut;  //this structure means that the first copy of a
    private int totalFines;

    /**
     * A constructor to create a new Visitor, using the first name, last name, address, phone number, and ID
     *
     * @param firstName  The first name of the visitor
     * @param lastName  The last name of the visitor
     * @param address  The address of the visitor
     * @param phoneNumber  The phone number of the visitor
     * @param ID  The unique ID number of the visitor

    public Visitor(String firstName, String lastName, String address, int phoneNumber, int ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ID = ID;
    }
    **/

    /**
     * A constructor to create a new Visitor, using the first name, last name, address, phone number, ID, and list of books Checked out
     *
     * @param firstName  The first name of the visitor
     * @param lastName  The last name of the visitor
     * @param address  The address of the visitor
     * @param phoneNumber  The phone number of the visitor
     * @param ID  The unique ID number of the visitor
     * @param booksCheckedOut A list of currenty checked out books by the visitor, organized by the book's ISBN
     */
    public Visitor(String firstName, String lastName, String address, int phoneNumber, int ID, ArrayList<CheckedOut> booksCheckedOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ID = ID;
        this.booksCheckedOut = booksCheckedOut;
    }

    /**
     * returns the first name of the Visitor
     * @return the first name of the visitor
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the visitor to the entered string
     * @param firstName the new first name of the visitor
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * returns the last name of the Visitor
     * @return the last name of the visitor
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the Last name of the visitor to the entered string
     * @param lastName the new Last name of the visitor
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the address of the visitor
     * @return the address of the visitor
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the visitor to the entered string
     * @param address the new address of the visitor
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the phone number of the visitor
     * @return the phone number of the visitor
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the visitor to the entered integer
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * returns the unique ID number of the visitor
     * @return the unique ID number of the visitor
     */
    public int getID() {
        return ID;
    }

    //there is no "set" method for the ID because it should never change.

    public int getTotalFines() {
        return totalFines;
    }

    /**
     * adds a book to the list of books the visitor currently has checked out
     * @param book the book being checked out
     * @return if the book was successfully added to the list of books checked out.
     */
    public Boolean addCheckedOutBook(Book book)
    {
        if(this.booksCheckedOut.size() < 5) {
            int ISBN = book.getISBN();
            Date CheckoutDate = new Date();
            CheckedOut checkout = new CheckedOut(ISBN, this.ID, CheckoutDate);
            this.booksCheckedOut.add(checkout);
            return true;
        }
        return false;
    }

    public ArrayList<CheckedOut> getCheckouts() {
        return this.booksCheckedOut;
    }

    /**
     * removes a book from the list of books the visitor currently has checked out
     * and uses the strategy pattern to calculate a fine (context)
     * @param checkedOut the book being checked in
     * @return if the book was successfully removed from the list of books checked out.
     */
    public int checkInBook(CheckedOut checkedOut) {
        Date today = new Date(0);
        int cost;
        long difference;
        long differenceInDays;

        difference = Math.abs(today.getTime() - checkedOut.getDueDate().getTime());
        differenceInDays = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

        if (differenceInDays <= 7) {
            Transaction transaction = new Transaction(new ReturnedWithin7Days());
            cost = transaction.calculate(checkedOut);
        } else if (differenceInDays <= 14) {
            Transaction transaction = new Transaction(new Returned1WeekLate());
            cost = transaction.calculate(checkedOut);
        } else if (differenceInDays <= 77) {
            Transaction transaction = new Transaction(new Returned2To11WeeksLate());
            cost = transaction.calculate(checkedOut);
        } else {
            Transaction transaction = new Transaction(new Returned12OrMoreWeeksLate());
            cost = transaction.calculate(checkedOut);
        }

        this.totalFines += cost;
        this.booksCheckedOut.remove(checkedOut);
        return cost;

    }



  }
