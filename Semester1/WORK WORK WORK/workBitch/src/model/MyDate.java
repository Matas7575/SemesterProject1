package model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/*** A class representing a date.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class MyDate
{
  private int day;
  private int month;
  private int year;

  /**
   * A set method which takes 3 int variables and initializes them into the private variables
   * @param day the method receives an  int number to initialize the day
   * @param month the method receives an  int number to initialize the month
   * @param year the method receives an  int number to initialize the year
   */

  public void set(int day, int month, int year)
  {
    this.year = Math.abs(year);
    this.month = Math.abs(month);
    this.day = Math.abs(day);
    if (day > numberOfDaysInMonth())
      this.day = numberOfDaysInMonth();
  }

  /**
   * Three-argument constructor. Illegal dates are converted to legal dates;
   * a negative year is set to its positive counterpart, a month less than 1 is set to 1 and more than 12 is set to 12.
   * A day less than 1 is set to 1 and a day more than the last day of
   * the specified month is set to this last day
   * @param day the day
   * @param month the month as an integer between 1-12
   * @param year the year
   */

  public MyDate(int day, int month, int year)
  {
    set(day, month, year);
  }

  /**
   * AN overloaded constructor which doesn't take any variables and initializes the local date from the machine
   */

  public MyDate()
  {
    LocalDate today = LocalDate.now();
    this.year = today.getYear();
    this.month = today.getMonthValue();
    this.day = today.getDayOfMonth();
  }

  /*
   * Three-argument constructor. Illegal dates are converted to legal dates;
   * a negative year is set to its positive counterpart, a month name that is not correct will be set to January by default.
   * A day less than 1 is set to 1 and a day more than the last day of
   * the specified month is set to this last day
   * @param day the day
   * @param month the month as a String representing month name
   * @param year the year
   *

  public MyDate(int day, String month, int year)
  {
    set(day,convertToMonthNumber(month),year);
  }

  /**
   * A method that converts the name of the month stored in the monthName variable into a number
   * @param monthName from which the program receives the month name in a String form
   * @return the number of the month as an int
   *

  public static int convertToMonthNumber(String monthName)
  {
    switch (monthName)
    {
      case "February":
        return 2;
      case "March":
        return 3;
      case "April":
        return 4;
      case "May":
        return 5;
      case "June":
        return 6;
      case "July":
        return 7;
      case "August":
        return 8;
      case "September":
        return 9;
      case "October":
        return 10;
      case "November":
        return 11;
      case "December":
        return 12;
      default:
        return 1;
    }
  }
*/
  /**
   * A getter method for day variable
   * @return the number stored in the day variable as an int
   */

  public int getDay()
  {
    return day;
  }

  /**
   * A getter method for month variable
   * @return the number stored in the month variable as an int
   */

  public int getMonth()
  {
    return month;
  }

  /**
   * A getter method for year variable
   * @return the number stored in the year variable as an int
   */

  public int getYear()
  {
    return year;
  }

  /**
   *  A boolean method that checks if the year is a leap year or not
   * @return true if is a leap year/  false if is not a leap year
   */

  public boolean isLeapYear()
  {
    return (year % 4 == 0) && ((year % 100 != 0) || year % 400 == 0);
  }

  /**
   * A getter method that returns the number of days in a month.
   * @return the number of days in a month represented by an int
   */

  public int numberOfDaysInMonth()
  {
    switch (month)
    {
      case 2:
        if (isLeapYear())
        {
          return 29;
        }
        else
        {
          return 28;
        }

      case 4:

      case 6:

      case 9:

      case 11:
        return 30;

      default:
        return 31;
    }

  }

  /**
   * A void method that adds the day variable by one and checks if the month can have that many days.
   * If the day is at the month's last day the month is added too and the day will become 1
   * if the day is the max number of days in a month and the month is 12 the year is incremented by 1 and the month becomes 1 and the day becomes 1
   */

  public void stepForwardOneDay()
  {
    {
      day++;
      if (day > numberOfDaysInMonth())
      {
        day = 1;
        month++;

        if (month > 12)
        {
          month = 1;
          year++;
        }
      }
    }
  }

  /**
   * A method that adds the day variable by how much was specified through the parameter
   * It calls the stepForwardOneDay method
   * @param days specifies the number of times stepForwardOneDay is called
   */

  public void stepForward(int days)
  {
    for (int i=0; i < days; i++)
    {
      stepForwardOneDay();
    }
  }

  /**
   * A method that checks if a MyDate object is before another MyDate object or not
   * @param other it provides the method with a MyDate object
   * @return true if the MyDate object provided by "other" is before the current date stored in the class
   * false if the MyDate object provided by "other" is after the current date stored in the class
   */

  public boolean isBefore(MyDate other)
  {
    if (this.getYear() == other.getYear())
    {
      if (this.getMonth() == other.getMonth())
      {
        return this.getDay() < other.getDay();
      }
      else
        return (this.getMonth() < other.getMonth());
    }
    else
      return (this.getYear() < other.getYear());
  }

  /**
   * The method calculates the days between two dates
   * @param other a MyDate object
   * @return an integer value representing the days between the date provided by the parameter and the date stored in the class
   */

  public int daysBetween(MyDate other)
  {
    MyDate date1 = this.copy();
    MyDate date2 = other.copy();
    int daysBetween = 0;

    if (date1.isBefore(date2))
    {
      while (date1.isBefore(date2))
      {
        date1.stepForwardOneDay();
        daysBetween++;
      }
    }
    else
    {
      while (date2.isBefore(date1))
      {
        date2.stepForwardOneDay();
        daysBetween++;
      }
    }
    return daysBetween;
  }

  /**
   * A copy method that copies the MyDate object stored in the class
   * @return a new MyDate object
   */

  public MyDate copy()
  {
    return new MyDate (day,month,year);
  }

  /**
   * A method that  calculates the number of weeks between two dates
   * @param otherDate providing a MyDate object for the method
   * @return the number of weeks between the otherDate object and the date currently stored in the class
   */

  public int weeksBetween(MyDate otherDate)
  {
    return daysBetween(otherDate) / 7;
  }

  /**
   * A toString method
   * @return a String containing every variable of this class
   */

  public String toString()
  {
    String s = "";
    if (day < 10)
      s += "0";
    s += day;
    s += "/";

    if (month < 10)
      s += "0";
    s += month;
    s += "/";
    s += year;

    return s;
  }

  /**
   * A method that calculates the number of the week
   * @param date provides a MyDate object
   * @return an integer representing the number of the week we currently are in
   */

  static public int getWeekNumber(MyDate date)
  {
    MyDate firstMonday = new MyDate(1,1,date.getYear());
    while (!(MyDate.getWeekDay(firstMonday).equals("MON")))
    {
      firstMonday.stepForwardOneDay();
    }

    if (firstMonday.isBefore(date))
    {
      return firstMonday.weeksBetween(date) + 1;
    }
    return 52;
  }

  /**
   * A method that find which day of the week is represented by the provided date
   * @param myDate which provides a date of which it will find the name of day
   * @return a String which represents the day of the week
   */

  public static String getWeekDay(MyDate myDate) {
    LocalDate date = LocalDate.of(myDate.getYear(),myDate.getMonth(),myDate.getDay());
    DayOfWeek day = date.getDayOfWeek();
    int value = day.getValue();

    switch (value) {
      case 1:
        return "MON";
      case 2:
        return "TUE";
      case 3:
        return "WED";
      case 4:
        return "THU";
      case 5:
        return "FRI";
      case 6:
        return "SAT";
      default:
        return "SUN";
    }
  }

  /**
   * An equals method which checks two dates to see if they're the same
   * @param date provides the date that is going to compare to
   * @return true if the dates are equal
   * false if they are not equal
   */

  public boolean equals(Object date) // 6 time units
  {
    if (!(date instanceof MyDate)) // 1 time unit
    {
      return false; // 1 time unit
    }

    MyDate other = (MyDate)date; // 1 time unit
    return (this.day == other.day && this.month == other.month && this.year == other.year); // 4 time units
  }
}
