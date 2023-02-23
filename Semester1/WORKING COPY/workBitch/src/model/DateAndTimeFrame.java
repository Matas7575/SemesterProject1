package model;

import java.util.Date;
import java.util.Objects;

/**
 * A class that stores a date and a time frame
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public class DateAndTimeFrame
{
  private MyDate date;
  private MyTime startTime;
  private MyTime endTime;

  /**
   * a Constructor to initialise all instance variables for class DateAndTimeFrame
   *
   * @param date      from which the program receives the date in a MyDate format
   * @param startTime from which the program receives the startTime in a MyTime format
   * @param endTime   from which the program receives the endTime in a MyTime format
   */
  public DateAndTimeFrame(MyDate date, MyTime startTime, MyTime endTime)
  {
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * another constructor to initialise instance variables
   *
   * @param date            from which the program receives the date in a MyDate format
   * @param startTime       from which the program receives the startTime in a MyTime format
   * @param numberOfLessons from which the program receives the numberOfLessons in an int format
   */
  public DateAndTimeFrame(MyDate date, MyTime startTime, int numberOfLessons)
  {
    this.date = date;
    this.startTime = startTime;
    this.endTime = Session.defineEndTime(startTime, numberOfLessons);
  }

  /**
   * a get method for a date variable
   *
   * @return a MyDate type variable
   */
  public MyDate getDate()
  {
    return date;
  }

  /**
   * a get type method for startTime vatiable
   *
   * @return a MyTime type variable
   */
  public MyTime getStartTime()
  {
    return startTime;
  }

  /**
   * a get method for endTime variable
   *
   * @return a MyTime type variable
   */
  public MyTime getEndTime()
  {
    return endTime;
  }

  /**
   * a method that checks if there are overlaps with other times in specific timeframes
   *
   * @param other from which the program receives the other in a DateAndTimeFrame format
   * @return a boolean type variable
   */
  public boolean checkForOverlaps(DateAndTimeFrame other) //24 time units
  {
    if (!(this.date.equals(other.date))) //7 time units
    {
      return false; //1 time unit
    }

    if (this.startTime.equals(other.startTime)) // 6 time units
    {
      return true; // 1 time unit
    }

    else if (other.startTime.isBefore(this.endTime) && this.startTime.isBefore(
        other.endTime)) //5 time units
    {
      return true; //1 time unit
    }

    else if (this.startTime.isBefore(other.endTime) && other.startTime.isBefore(
        this.endTime)) //5 time units
    {
      return true; //1 time unit
    }

    return false; //1 time unit
  }

  /**
   * a toString method to put all instance variables to a String
   *
   * @return a String type variable
   */
  @Override public String toString()
  {
    return date + " - " + startTime + " - " + endTime;
  }

  /**
   * A standard equals method.
   *
   * @param o Object to be compared
   * @return boolean - whether the Object given is equal to "this" DateAndTimeFrame
   */
  @Override public boolean equals(Object o)
  {
    if (!(o instanceof DateAndTimeFrame))
    {
      return false;
    }
    DateAndTimeFrame other = (DateAndTimeFrame)o;
    return (other.date.equals(this.date) && other.startTime.equals(this.startTime) && other.endTime.equals(this.endTime));
  }

  /**
   * @param other DateAndTimeFrame object to be compared with.
   * @return boolean - whether the "this" DateAndTimeFrame is before the given one.
   */
  public boolean isBefore(DateAndTimeFrame other)
  {
    if (this.date.isBefore(other.date))
    {
      return true;
    }
    else if (other.date.isBefore(this.date))
    {
      return false;
    }
    else
      return this.startTime.isBefore(other.startTime);
  }

}


