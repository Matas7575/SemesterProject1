package model;
/*** A class representing the time.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public class MyTime
{
  private int hour;
  private int minute;

  /**
   * constructor for class MyTime to initialise all instance variables. if the hour of minute is illegal value that is set by the program
   * it will throw an illegal argument exception
   * @param hour from which the program receives the hour in an int format
   * @param minute from which the program receives the minute in an int format
   */
  public MyTime(int hour, int minute)
  {
    if (hour < 0)
    {
      throw new IllegalArgumentException("Hour cannot be negative.");
    }
    else if (hour > 23)
    {
      throw new IllegalArgumentException("Hour cannot be more than 23.");
    }
    else if (minute < 0)
    {
      throw new IllegalArgumentException("Minute cannot be negative.");
    }

    else if (minute > 59)
    {
      throw new IllegalArgumentException("Minute cannot be more than 59.");
    }
    this.hour = hour;
    this.minute = minute;
  }
  /**
   * a get method for hour
   * @return an int type variable
   */
  public int getHour()
  {
    return hour;
  }
  /**
   * a get method for minute
   * @return an int type variable
   */
  public int getMinute()
  {
    return minute;
  }

  /**
   * this method checks if time is before the other time
   * @param other from which the program receives the other in a MyTime format
   * @return a boolean type variable
   */
  public boolean isBefore(MyTime other) //2 time units
  {
    if (this.hour < other.hour) // 1 time unit
    {
      return true; //1 time unit
    }
    else if (this.hour > other.hour) //1 time unit
    {
      return false; //1 time unit
    }
    else if (this.minute < other.minute) //1 time unit
    {
      return true; //1 time unit
    }
    else
    {
      return false; //1 time unit
    }
  }
  /**
   * a method that gets all instance variables in a string and makes it look like a timer(adds 0 if the time is one digit and adds ":" in between instance variables)
   * @return string type variable
   */
  public String toString()
  {
    String s = "";
    if (hour < 10) {
      s+="0";
    }
    s+= hour + ":";
    if (minute < 10)
    {
      s+="0";
    }
    s+= minute;

    return s;
  }
  /**
   * an equals method which checks the other object if it is the same
   * @param time from which the program receives the time in an Object format
   * @return a boolean type variable
   */
  public boolean equals(Object time) //5 time units
  {
    if (!(time instanceof MyTime)) // 1 time unit
    {
      return false; // 1 time unit
    }

    MyTime other = (MyTime)time; //1 time unit
    return (other.hour == this.hour && other.minute == this.minute); // 3 time units
  }

  public MyTime copy()
  {
    return new MyTime(hour,minute);
  }
}
