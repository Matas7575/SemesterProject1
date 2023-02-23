package model;

import java.util.ArrayList;
/**
 * A class that provides a list of all courses
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public class AllCoursesList
{
  private ArrayList<Course> allCourses;

  /**
   * a Constructor to initialise Course arrayList
   */
  public AllCoursesList()
  {
    allCourses = new ArrayList<>();
  }

  /**
   * an add method to add courses to an arrayList
   * @param course from which the program receives the course in a Course format
   */
  public void addCourse(Course course)
  {
    allCourses.add(course);
  }

  /**
   * a size method to get the arrayList size
   * @return an int type variable
   */
  public int size()
  {
    return allCourses.size();
  }

  /**
   * a getter method for course from array list by its index
   * @param index from which the program receives the index in an int format
   * @return the course locate at the specified index
   */
  public Course getCourse(int index)
  {
    return allCourses.get(index);
  }

  /**
   * a get method for course which loops through each course in an arrayList and finds it by its semester, classxzy and title.
   * If it doesn't find it throws an illegalArgumentException
   * @param semester from which the program receives the semester in an int format
   * @param classxyz from which the program receives the classxyz in a String format
   * @param title from which the program receives the title in a String format
   * @return a Course type variable
   */
  public Course getCourse(int semester, String classxyz, String title)
  {
    for (int i =0; i < allCourses.size(); i++)
    {
      if (semester == allCourses.get(i).getSemester() &&
              classxyz.equals(allCourses.get(i).getClassxyz()) &&
              title.equals(allCourses.get(i).getTitle()))
      {
        return allCourses.get(i);
      }
    }
    throw new IllegalArgumentException("Course not found.");
  }

  /**
   * a toString method to put instance variable in a String type
   * @return a String type variable
   */
  @Override public String toString()
  {
    return "AllCoursesList{" + "allCourses=" + allCourses + '}';
  }
}
