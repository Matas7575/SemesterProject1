package model;
/*** A class representing a student.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public class Student extends ViaUser
{
  private String name;
  private int semester;
  private String classxyz;

  /**
   * a Constructor for Student class that initialises all instance variables including its superclasses variables
   * @param viaID from which the program receives the viaID in a string format
   * @param name from which the program receives the name in a string format
   * @param semester from which the program receives the semester in an int format
   * @param classxyz from which the program receives the classxyz in a string format
   */
  public Student(String viaID, String name, int semester, String classxyz)
  {
    super(viaID);
    this.name = name;
    this.semester = semester;
    this.classxyz = classxyz;
  }

  /**
   * a method to set a semester to a semester that is inputted
   * @param semester from which the program receives the semester in an int format
   */

  public void setSemester(int semester)
  {
    this.semester = semester;
  }

  /**
   * a setter method that sets a classxyz variable to an inputted value of String type
   * @param classxyz
   */
  public void setClassxyz(String classxyz)
  {
    this.classxyz = classxyz;
  }

  /**
   * a setter method that sets a classxyz and a semester to an inputted value of int for the semester and type String for the class xyz
   * @param semester from which the program receives the semester in an int format
   * @param classxyz from which the program receives the classxyz in a string format
   */

  public void setSemesterAndClass(int semester, String classxyz)
  {
    setSemester(semester);
    setClassxyz(classxyz);
  }

  /**
   * a toString method that returns name instance variable
   * @return returns a String type variable
   */

  @Override public String toString()
  {
    return super.toString() + "; Name: " + name;
  }

  /**
   * a getter for a type of via user
   * @return returns a String type variable
   */
  public String getTypeOfVIAUser()
  {
    return "Student";
  }

  /**
   * a getter for a superclass ViaID
   * @return a String type variable
   */
  public String getViaID()
  {
    return super.getViaID();
  }

  /**
   * a getter for a students semester
   * @return an in type variable
   */
  public int getSemester()
  {
    return semester;
  }

  /**
   * a getter for classxyz variable
   * @return a String type variable
   */
  public String getClassxyz()
  {
    return classxyz;
  }

  /**
   * a getter for a semester and class variable that connects them in a String
   * @return a String type variable
   */
  public String getClassFull()  // 1 time units
  {
    return semester + "" + classxyz; // 1 time units
  }

  /**
   * a getter for a students name
   * @return a String type variable
   */
  public String getName() {
    return name;
  }

  /*
  public void addCourse(Course course)
  {
    CourseList personalCourseList = this.getCourseList();
    personalCourseList.makePersonal();
    personalCourseList.setPersonalViaID(this.getViaID());
    personalCourseList.addCourse(course);
    this.setCourseList(personalCourseList);
  }

  public void removeCourse(Course course)
  {
    CourseList personalCourseList = this.getCourseList();
    personalCourseList.makePersonal();
    personalCourseList.setPersonalViaID(this.getViaID());
    personalCourseList.removeCourse(course);
    this.setCourseList(personalCourseList);
  }

   */
}
