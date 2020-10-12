package main.Models;

import java.util.Date;

/**
 * A model that represents an individual visit
 *
 * @author Alanna Morris
 */
public class Visit {

    private int VisitorID;
    private Date ArrivalTme;
    private Date DepartureTime;

    /**
     * Creates a new visit object with the current date/time as the arrival time
     * @param VisitorID the ID of the visitor
     */
    public Visit(int VisitorID) {
        // TODO add error checking for start time while library is open
        this.VisitorID = VisitorID;
        ArrivalTme = new Date();
    }

    /**
     * Updates the departure time to end the visit
     */
    public void endVisit() {
        DepartureTime = new Date();
    }

    //TODO add a function to close all open visits when the library closes, may be in APPL package

}
