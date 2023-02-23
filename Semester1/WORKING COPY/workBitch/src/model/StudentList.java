package model;
import java.util.*;
/*** A class representing a list of students.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public class StudentList
{
  private ArrayList<Student> students;

  /**
   * a constructor for the class StudentList that initialises Student arrayList
   */
  public StudentList()
  {
    this.students = new ArrayList<>();
  }

  /**
   * a getter method to get a student by his or hers viaID
   * @param viaID from which the program receives the viaID in a string format
   * @return returns a Student format variable
   */

  public Student getStudent(String viaID)
  {
    Student studentFound = null;
    for (Student student : students)
    {
     if(student.getViaID().equals(viaID))
     {
       studentFound = student;
       break;
     }
    }
    return studentFound;
  }

  /**
   * a getter for a student by his index
   * @param index from which the program receives the index in an int format
   * @return returns a student format variable
   */

  public Student getStudent(int index) // 2 time units
  {
    return students.get(index); // 2 time units
  }

  /**
   * a method that adds a student to the array list
   * @param student from which the program receives the student in a Student format
   */

  public void addStudent(Student student)
  {
    students.add(student);
  }

  /**
   * a method that shows the size of the arrayList
   * @return returns an int type variable
   */
  public int size() // 2 time units
  {
    return students.size(); // 2 time units
  }

  /**
   * a method to return all instance variables in a string format
   * @return returns a String type variable
   */
  @Override public String toString()
  {
    return "StudentList{" + "students=" + students + '}';
  }
}

