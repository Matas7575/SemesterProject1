package model;
/*** A class representing a user of the program.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public abstract class ViaUser
{
  private String viaID;
  private CourseList courseList;

  /**
   * a constructor to initialise all instance variables
   * @param viaID from which the program receives the viaID in a String format
   */
  public ViaUser(String viaID)
  {
    this.viaID = viaID;
    this.courseList = null;
  }

  /**
   * a get method for the courseList
   * @return a CourseList type variable
   */
  public CourseList getCourseList()
  {
    return courseList;
  }

  /**
   * a set method to change the courseList
   * @param courseList from which the program receives the courseList in a CourseList format
   */
  public void setCourseList(CourseList courseList)
  {
    this.courseList = courseList; //1 time units
  }

  /**
   * a get method for specific type of via user
   * @return a String type variable
   */
  public abstract String getTypeOfVIAUser();

  /**
   * a toString method to make getTypeOfVIAUser and viaID variables into a string
   * @return a String type variable
   */
  public String toString()
  {
    return getTypeOfVIAUser() + ". VIA ID:" + viaID;
  }

  /**
   * a get type method that gives viaID
   * @return a String type variable
   */
  public String getViaID()
  {
    return viaID;
  }
}
