package model;

/*** A class representing a teacher.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class Teacher extends ViaUser
{
  /**
   * a constructor that initialises instance variables if it's superclass
   * @param viaID from which the program receives the viaID in a string format
   */
  public Teacher(String viaID)
  {
    super(viaID);
  }

  /**
   * a method to return all instance variables of this class
   * @return returns all instance variables in a string format
   */
  @Override public String toString()
  {
    return super.toString();
  }

  /**
   * a getter for what type of VIA user it is
   * @return returns a string type variable
   */
  public String getTypeOfVIAUser()
  {
    return "Teacher";
  }

  /**
   * a getter for teachers viaID
   * @return returns a string type variable
   */

  public String getViaID()
  {
    return super.getViaID();
  }
}
