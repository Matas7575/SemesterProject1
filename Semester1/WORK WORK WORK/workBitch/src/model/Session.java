package model;

import java.time.LocalDate;

/** A class representing a session.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class Session
{
  private String dayOfTheWeek;
  private int numberOfLessons;
  private String courseTitle;
  private MyTime startTime;
  private MyTime endTime;
  private LocalDate lastEdited;
  private MyDate sessionDate;
  private int weekNumber;
  private Classroom classroom;

  /**
   * an array that lists all the possible starting times
   */
  public static final MyTime[] POSSIBLE_STARTING_TIMES = { new MyTime(8,20), new MyTime(9,10), new MyTime(10,15),
  new MyTime(11,05), new MyTime (11,55), new MyTime(12,45), new MyTime(13,35), new MyTime(14,30),
  new MyTime(15,20), new MyTime(16,10), new MyTime(17,00)};
  /**
   * an array that lists all the possible end times
   */
  public static final MyTime[] POSSIBLE_END_TIMES = {new MyTime(9,05), new MyTime(9,55), new MyTime(11,00),
      new MyTime(11,50), new MyTime(12,40), new MyTime(13,30), new MyTime(14,20), new MyTime(15,15),
      new MyTime(16,05), new MyTime(16,55), new MyTime(17,45), new MyTime(18,45)};

  /**
   * a constructor for session class which initialises all instance variables
   * @param courseTitle from which the program receives the courseTitle in a String format
   * @param numberOfLessons from which the program receives the numberOfLessons in an int format
   * @param startTime from which the program receives the startTime in a MyDate format
   * @param sessionDate from which the program receives the sessionDate in a MyDate format
   * @param classroom from which the program receives the classroom in a Classroom format
   */
  public Session(String courseTitle, int numberOfLessons, MyTime startTime, MyDate sessionDate, Classroom classroom)
  {
    this.numberOfLessons = numberOfLessons;
    this.startTime = startTime;
    this.sessionDate = sessionDate;
    this.lastEdited = LocalDate.now();
    this.endTime = defineEndTime(startTime, numberOfLessons);
    this.classroom = classroom;
    this.courseTitle = courseTitle;
    this.dayOfTheWeek = MyDate.getWeekDay(sessionDate);
    this.weekNumber = MyDate.getWeekNumber(sessionDate);
  }

  /**
   * A is before method which verifies time frames
   * @param other the object compared with "this" object
   * @return a boolean value
   */

  public boolean isBefore(Session other)
  {
    DateAndTimeFrame thisDATM = new DateAndTimeFrame(this.getSessionDate(), this.getStartTime(), this.getNumberOfLessons());
    DateAndTimeFrame otherDATM = new DateAndTimeFrame(other.getSessionDate(), other.getStartTime(), other.getNumberOfLessons());
    return (thisDATM.isBefore(otherDATM));
  }

  /**
   * A set method which sets a classroom to the session
   * @param classsroom the classroom
   */

  public void setClassroom(Classroom classsroom)
  {
    this.classroom = classsroom;
  }

  /**
   *a method that sets the start and end time for the session
   * @param startTime from which the program receives the startTime in a MyTime format
   */
  public void setStartTime(MyTime startTime)
  {
    this.startTime = startTime;
    this.endTime = defineEndTime(startTime, numberOfLessons);
  }

  /**
   * a getter method to get dayOfTheWeek
   * @return a String type variable
   */
  public String getDayOfTheWeek() {
    return dayOfTheWeek;
  }

  /**
   * a getter for a courseTitle variable
   * @return a String type variable
   */
  public String getCourseTitle()
  {
    return courseTitle;
  }

  /**
   * a method to set a number of lessons
   * @param numberOfLessons from which the program receives the numberOfLessons in an int format
   */
  public void setNumberOfLessons(int numberOfLessons)
  {
    this.numberOfLessons = numberOfLessons;
    this.endTime = defineEndTime(startTime, numberOfLessons);
  }

  /**
   * a set method to set the sessionDate
   * @param sessionDate from which the program receives the sessionData in a MyDate format
   */
  public void setSessionDate(MyDate sessionDate)
  {
    this.sessionDate = sessionDate;
    this.dayOfTheWeek = MyDate.getWeekDay(sessionDate);
  }

  /**
   * a method to get a startTime of the session
   * @return a MyTime type variable
   */
  public MyTime getStartTime()
  {
    return startTime;
  }

  /**
   * a getter method to return endTime variable
   * @return a MyTime variable
   */
  public MyTime getEndTime()
  {
    return endTime;
  }

  /**
   * a getter method to get the lastEdited time
   * @return a LocalDate type variable
   */
  public LocalDate getLastEdited()
  {
    return lastEdited;
  }

  /**
   * a getter method to get a sessionDate
   * @return a MyDate type variable
   */
  public MyDate getSessionDate()
  {
    return sessionDate;
  }

  /**
   * a getter method that returns the numberOfLessons
   * @return an int type variable
   */
  public int getNumberOfLessons()
  {
    return numberOfLessons;
  }

  /**
   * a getter method that gets the classroom variable
   * @return a Classroom type variable
   */
  public Classroom getClassroom()
  {
    return classroom;
  }

  /**
   * a method that fetches the start times and number of lessons to determine the length of the session
   * @param startTime from which the program receives the numberOfLessons in an int format
   * @param numberOfLessons from which the program receives the startTime in a MyTime format
   * @return a MyTime type variable
   */
  static public MyTime defineEndTime(MyTime startTime, int numberOfLessons)
  {
    int indexStartTime = 0;

    for (int i = 0; i < POSSIBLE_STARTING_TIMES.length; i++)
    {
      if (startTime.equals(POSSIBLE_STARTING_TIMES[i]))
      {
        indexStartTime = i;
      }
    }
    return POSSIBLE_END_TIMES[indexStartTime+numberOfLessons-1];
  }

  /**
   * this method checks if the session and the other added session doesn't overlap with each other
   * @param other from which the program receives the other in a Session format
   * @return a boolean type variable
   */

  public boolean checkForTimeOverlaps(Session other)
  {
    if (!(this.sessionDate.equals(other.sessionDate)))
    {
      return false;
    }

    if (this.startTime.equals(other.startTime))
    {
      return true;
    }

    else if (other.startTime.isBefore(this.endTime) && this.startTime.isBefore(other.endTime))
    {
      return true;
    }

    else return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
  }

  /**
   * a method that makes a copy of the Session
   * @return a Session type variable
   */
  public Session copy()
  {
    return new Session(courseTitle, numberOfLessons, startTime.copy(), sessionDate.copy(), classroom);
  }

  /**
   * a toString method that gets all of the instance variables to a string
   * @return a String type variable
   */
  @Override public String toString()
  {
    return courseTitle + " - " + sessionDate + " - " + startTime + " - " + endTime + " in " + classroom;  }
}
