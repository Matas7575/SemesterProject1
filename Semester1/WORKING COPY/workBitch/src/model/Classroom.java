package model;

import java.util.ArrayList;
/*** A class representing a classroom.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public class Classroom
{
  private String room;
  private int capacity;
  private ArrayList<DateAndTimeFrame> isBookedWhen;

  /**
   * a constructor for the class Classroom to initialise all instance variables
   * @param room from which the program receives the room in a String format
   * @param capacity from which the program receives the capacity in an int format
   */
  public Classroom(String room, int capacity)
  {
   this.room = room;
   this.capacity = capacity;
   this.isBookedWhen = new ArrayList<>();
  }


  /**
   * a getter method for all of the time frames that are booked
   * @return an arrayList type variable
   */
  public ArrayList<DateAndTimeFrame> getTimeframesBooked()
  {
    return isBookedWhen;
  }

  /**
   * a get method for room variable
   * @return a String type variable
   */
  public String getRoom() {
    return room;
  }

  /**
   * a getter method for capacity variable
   * @return an int type variable
   */
  public int getCapacity()
  {
    return capacity;
  }

  /**
   * a set type method to change the capacity
   * @param newCapacity from which the program receives the newCapacity in an int format
   */
  public void setCapacity(int newCapacity)
  {
    this.capacity = newCapacity;
  }

  /**
   * a method that adds isBookedWhen objects to the arrayList with date, startTime and endTime variables
   * @param date from which the program receives the date in a MyDate format
   * @param startTime from which the program receives the startTime in a MyDate format
   * @param endTime from which the program receives the endTime in a MyDate format
   */
  public void bookClassroom(MyDate date, MyTime startTime, MyTime endTime)
  {
    this.isBookedWhen.add(new DateAndTimeFrame(date,startTime,endTime));
  }

  /**
   * a method that adds isBookedWhen objects to the arrayList with date, startTime and numberOfLessons variables
   * @param date from which the program receives the date in a MyDate format
   * @param startTime from which the program receives the startTime in a MyDate format
   * @param numberOfLessons from which the program receives the numberOfLessons in an int format
   */
  public void bookClassroom(MyDate date, MyTime startTime, int numberOfLessons)
  {
    this.isBookedWhen.add(new DateAndTimeFrame(date,startTime,numberOfLessons));
  }

  /**
   * a method that adds isBookedWhen objects to the arrayList with a timeFrame variable
   * @param timeFrame from which the program receives the timeFrame in an DateAndTimeFrame format
   */
  public void bookClassroom(DateAndTimeFrame timeFrame)
  {
    this.isBookedWhen.add(timeFrame);
  }
  /**
   * a method that takes out objects from of isBookedWhen arrayList that contain date, startTime and endTime specific variables
   * @param date from which the program receives the date in a MyDate format
   * @param startTime from which the program receives the startTime in a MyDate format
   * @param endTime from which the program receives the endTime in a MyDate format
   */
  public void unbookClassroom(MyDate date, MyTime startTime, MyTime endTime)
  {
    DateAndTimeFrame unbook = new DateAndTimeFrame(date,startTime,endTime);
    for (int i = 0; i < isBookedWhen.size(); i++)
    {
      if (unbook.equals(isBookedWhen.get(i)))
      {
        this.isBookedWhen.remove(isBookedWhen.get(i));
      }
    }
  }
  /**
   * a method that takes out objects from of isBookedWhen arrayList that contain date, startTime and numberOfLessons specific variables
   * @param date from which the program receives the date in a MyDate format
   * @param startTime from which the program receives the startTime in a MyDate format
   * @param numberOfLessons from which the program receives the numberOfLessons in an int format
   */
  public void unbookClassroom(MyDate date, MyTime startTime, int numberOfLessons)
  {
    DateAndTimeFrame unbook = new DateAndTimeFrame(date,startTime,Session.defineEndTime(startTime,numberOfLessons));
    for (int i = 0; i < isBookedWhen.size(); i++)
    {
      if (unbook.equals(isBookedWhen.get(i)))
      {
        this.isBookedWhen.remove(isBookedWhen.get(i));
      }
    }

  }
  /**
   * a method that takes out objects from of isBookedWhen arrayList that contain timeFrame specific variable
   * @param timeFrame from which the program receives the timeFrame in an DateAndTimeFrame format
   */
  public void unbookClassroom(DateAndTimeFrame timeFrame)
  {
    this.isBookedWhen.remove(timeFrame);
  }
  /**
   * a toString method that takes a room instance variable and puts it in a String
   * @return a String type variable
   */
  public String toString()
  {
    return room;
  }

  public boolean equals(Object o) {
    if (!(o instanceof Classroom))
    {
      return false;
    }
    Classroom other = (Classroom)o;
    return (this.room.equals(other.room) && (this.capacity == other.capacity));
  }
}
