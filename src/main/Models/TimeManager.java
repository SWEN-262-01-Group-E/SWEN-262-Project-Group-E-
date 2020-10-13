package main.Models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Anthony Ferraioli
 */
public class TimeManager implements Serializable {

    private static TimeManager instance;

    private Calendar calendar;
    private SimpleDateFormat format;
    private long lastUpdatedTime;
    private boolean allowDateManipulation;

    /*
        Retrieve the instance of the timeManager, so that all classes can manipulate the same instance
     */
    public static TimeManager getInstance()
    {
        if(instance == null) { instance = new TimeManager(); }

        return  instance;
    }

    /*
        The constructor for the class
     */
    private TimeManager()
    {
        //finds calendar instance
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        format = new SimpleDateFormat("yyyy-MM-dd");
        lastUpdatedTime = System.currentTimeMillis();
        allowDateManipulation = true;
    }

    /*
        Set the date specifically using a string. Used when first reading from file.
        Ensures that date cannot be changed once it is set.
     */
    public void setDate(String dateString) throws ParseException
    {
        if(!allowDateManipulation) return;

        calendar.setTime(format.parse(dateString));
        lastUpdatedTime = System.currentTimeMillis();
        allowDateManipulation = false;
    }

    /*
        Adds a specified number of days to the calendar
     */
    public void addDays(int days)
    {
        calendar.add(Calendar.DAY_OF_MONTH, days);
    }

    /*
        Adds a specified number of hours to the calendar
     */
    public void addHours(int hours)
    {
        calendar.add(Calendar.HOUR, hours);
    }

    /*
        Updates the calendar to be accurate with current time difference and returns a Date object
     */
    public Date getDate()
    {
        UpdateCalendar();
        return calendar.getTime();
    }

    /*
        Finds the difference in milliseconds from the last time the Calendar was checked and updates it based on that information
     */
    private void UpdateCalendar()
    {
        long newUpdatedTime = System.currentTimeMillis();

        //find the number of milliseconds passed since last date check
        int timeDifference = (int)(newUpdatedTime - lastUpdatedTime);

        //add the difference in milliseconds to calendar
        calendar.add(Calendar.MILLISECOND, timeDifference);

        //update the last updated time
        lastUpdatedTime = newUpdatedTime;
    }

    /*
        Returns a string of the current date
     */
    public String ToString()
    {
        return format.format(getDate());
    }
}
