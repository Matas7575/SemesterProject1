package model;

import java.time.LocalDate;
import java.util.ArrayList;

public interface VIAModel
{
  VIA loadInfoFromFile();
  void saveCurrentChanges(VIAModel viaObject);
  void switchStudentFromClass(Student student, int semester, String classxyz);
  void scheduleSession(Course course, int numberOfLessons, MyTime startTime, MyDate sessionDate, Classroom classroom);
  void scheduleSession(Session session, int semester, String classxyz);
  void addStudentToClass(String viaID, int semester, String classxyz);
  void removeStudentFromClass(String viaID);
  void addStudentToCourse(String viaID, Course course);
  void removeStudentFromCourse(String viaID, Course course);
  void assignAllStudentsToTheirCourseList();
  void assignStudentToCourseList(Student student);
  void rescheduleSession(Session session, MyDate newDate, MyTime newTime, int numberOfLessons, Classroom newClassroom);
  void unbookClassroom(Classroom unbookHer, MyDate date, MyTime startTime, int numLessons);
  boolean checkForTimeOverlaps(Session session1, Session session2);
  public void exportClassSessionList(int semester, String classxyz);
  public ArrayList<Classroom> returnAvailableClassrooms(MyDate date, MyTime startTime, int numberOfLessons);
  StudentList getStudentList();
  TeacherList getTeacherList();
  ClassroomList getClassroomList();
  CourseListList getAllCourseLists();
  AllCoursesList getAllCourses();
}
