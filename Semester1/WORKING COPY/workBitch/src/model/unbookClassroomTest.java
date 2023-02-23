package model;

public class unbookClassroomTest
{
  public static void main(String[] args)
  {
    Classroom classroom = new Classroom("AAA", 10);
    MyDate date = new MyDate(1,1,1);
    MyTime time = new MyTime(8,20);
    classroom.bookClassroom(date,time,1);
    System.out.println(classroom.getTimeframesBooked());

    MyDate date1 = new MyDate(1,1,1);
    MyTime time1 = new MyTime(8,20);


    classroom.unbookClassroom(date1,time1,1);
    System.out.println(classroom.getTimeframesBooked());
  }
}
