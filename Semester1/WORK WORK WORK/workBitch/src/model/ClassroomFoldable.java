package model;

/**
 * A class that provides a list with all the foldable classrooms
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class ClassroomFoldable extends Classroom
{
  private String foldableCounterpart;
  private boolean isFolded;

  /**
   * A three-argument constructor to initialize the variables and the super class
   * the isFolded variable is always initialized as false
   * @param room the room
   * @param capacity the capacity of the room
   * @param counterpart the counterpart of the classroom
   */

  public ClassroomFoldable(String room, int capacity, String counterpart)
  {
    super(room,capacity);
    this.foldableCounterpart = counterpart;
    this.isFolded = false;
  }

  /**
   * A get method which gets the foldableCounterpart variable
   * @return the foldableCounterpart
   */

  public String getCounterpart()
  {
    return foldableCounterpart;
  }

  /**
   * A void method which assigns to isFolded the value true
   */

  public void fold()
  {
    this.isFolded = true;
  }

  /**
   *  A void method which assigns to isFolded the value false
   */

  public void unfold()
  {
    this.isFolded = false;
  }

  /**
   * A toString method which prints a String line in the console
   * @return all the variables from the class
   */

  @Override
  public String toString() {
    return super.toString();
  }
}
