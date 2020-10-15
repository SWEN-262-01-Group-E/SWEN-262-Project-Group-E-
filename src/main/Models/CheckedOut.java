package main.Models;

import java.util.Date;
import java.util.Calendar;

/**
 * A class that holds a checked out book ISBN, the visitor ID, the checkout date,
 * and the due date
 *
 * @author Alanna Morris
 */

public class CheckedOut {

    private int ISBN;
    private int VisitorID;
    private Date CheckoutDate;
    private Date DueDate;

    public CheckedOut(int ISBN, int VisitorID, Date CheckoutDate) {
        this.ISBN = ISBN;
        this.VisitorID = VisitorID;
        this.CheckoutDate = CheckoutDate;

        Calendar newDate = Calendar.getInstance();
        newDate.setTime(CheckoutDate);
        newDate.add(Calendar.DATE, 7);
        DueDate = newDate.getTime();
    }

    public int getISBN() {
        return ISBN;
    }

    public Date getDueDate(){
        return DueDate;
    }
}
