package model;

import java.util.Date;

public class dateAndTimeFrameTest
{
  public static void main(String[] args)
  {
    DateAndTimeFrame la = new DateAndTimeFrame(new MyDate(1,1,1), new MyTime(2,22), new MyTime(3,33));
    DateAndTimeFrame ba = new DateAndTimeFrame(new MyDate(1,1,1), new MyTime(2,22), new MyTime(3,33));
    System.out.println(la.equals(ba));
  }
}
