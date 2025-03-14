package entertainment.pro.model;

import entertainment.pro.logic.parsers.TimeParser;
import java.text.ParseException;
import java.util.Date;


/**
 * Date wrapper class for having a date object and string date.
 */
public class MyDate implements Comparable<MyDate> {

    private boolean hasEndDate = false;
    private Date startdate = null;
    private Date enddate = null;
    private String startdateStr;
    private String enddateStr;

    /**
     * Constructor for class.
     * @throws ParseException when fail to parse a String that is ought to have a special format.
     */
    public MyDate(String s) {


        this.startdateStr = s;
        this.enddateStr = s;

        this.startdate = TimeParser.convertStringToDate(s);
        this.enddate = TimeParser.convertStringToDate(s);


        if (this.startdate != null) {
            this.startdateStr = TimeParser.convertDateToLine(this.startdate);
        }
        if (this.enddate != null) {
            this.enddateStr = TimeParser.convertDateToLine(this.enddate);
        }

    }

    /**
     * Constructor for class.
     */
    public MyDate(String startDate,  String endDate) {


        hasEndDate = true;

        this.startdateStr = startDate;
        this.enddateStr = endDate;

        this.startdate = TimeParser.convertStringToDate(startDate);
        this.enddate = TimeParser.convertStringToDate(endDate);


        if (this.startdate != null) {
            this.startdateStr = TimeParser.convertDateToLine(this.startdate);
        }

        if (this.enddate != null) {
            this.enddateStr = TimeParser.convertDateToLine(this.enddate);
        }

    }


    /**
     * Returns the start date object.
     * @return Date object of the Task
     */
    public Date getStartDate() {
        return this.startdate;

    }

    /**
     * Returns the end date object.
     * @return Date object of the Task
     */
    public Date getEndDate() {
        return this.enddate;

    }

    /**
     * converts the period to a string representation
     * @return a string of the period
     */
    @Override
    public String toString() {
        String returnStr = hasEndDate ? startdateStr + " to " + enddateStr : startdateStr;
        return returnStr;
    }

    /**
     * function to compare two dates
     * @param o: second object to compare the date to
     * @return an integer to determine if the date is lesser than or equal to the date compared to
     */
    @Override
    public int compareTo(MyDate o) {
        if (getEndDate() == null || o.getEndDate() == null) {
            return 0;
        }
        return getEndDate().compareTo(o.getEndDate());
    }
}
