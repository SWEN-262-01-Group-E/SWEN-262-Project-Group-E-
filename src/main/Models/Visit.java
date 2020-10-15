package main.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * A model that represents an individual visit
 *
 * @author Alanna Morris
 */
public class Visit {

    private long VisitorID;
    private Date ArrivalTime = null;
    private Date DepartureTime = null;

    /**
     * Creates a new visit object with the specified date/time as the arrival time
     *
     * @param VisitorID the ID of the visitor
     */
    public Visit(long VisitorID, Date arrivalTme) {

        this.VisitorID = VisitorID;
        ArrivalTime = arrivalTme;
    }

    /**
     * Updates the departure time to end the visit
     */
    public void endVisit(Date departureTime) {
        DepartureTime = departureTime;
    }

    public boolean getIsOngoingVisit() {
        return DepartureTime == null;
    }

    public long getVisitorID() {
        return VisitorID;
    }

    public Date getArrivalTime() {
        return ArrivalTime;
    }

    public long getLengthOfVisit() {
        if (DepartureTime != null)
            return DepartureTime.getTime() - ArrivalTime.getTime();

        return 0;
    }

    /**
     * returns the length of an ongoing visit
     *
     * @param now the current time and date
     * @return the time difference between the current time and the start time of the visit
     */
    public Long getCurrentLengthOfVisit(Date now) {
        return now.getTime() - ArrivalTime.getTime();
    }

    @Override
    public String toString() {
        return "VisitorID=" + VisitorID +
                ", ArrivalTime=" + ArrivalTime +
                '}';
    }

}