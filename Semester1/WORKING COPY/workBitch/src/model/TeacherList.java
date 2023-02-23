package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/*** A class representing a list of teachers.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public class TeacherList
{
  private ArrayList<Teacher> teachers;

  /**
   * Constructor for TeacherList class which only initialises the Teacher arrayList
   */
  public TeacherList()
  {
    this.teachers = new ArrayList<>();
  }

  /**
   * a getter for teacher arrayList
   * @return
   */

  public ArrayList<Teacher> getTeachers()
  {
    return teachers;
  }

  /**
   * getter for a specific teacher by his or hers viaID
   * @param viaID from which the program receives the viaID in a string format
   * @return returns Teacher type variable
   */
  public Teacher getTeacher(String viaID)
  {
    Teacher teacherFound = null;
    for (Teacher teacher : teachers)
    {
      if(teacher.getViaID().equals(viaID))
      {
        teacherFound = teacher;
        break;
      }
    }
    return teacherFound;
  }

  /**
   * a method that adds a teacher to the TeacherList
   * @param teacher from which the program receives a teacher in a Teacher format
   */
  public void addTeacher(Teacher teacher)
  {
    teachers.add(teacher);
  }

  /**
   * a method that gives a number of teachers
   * @return returns an int format variable
   */
  public int numberOfTeachers()
  {
    return teachers.size();
  }

  /**
   * a toString method to return instance variables as a string
   * @return returns a string type variable;
   */
  @Override public String toString()
  {
    return "TeacherList{" + "teachers=" + teachers + '}';
  }
}
