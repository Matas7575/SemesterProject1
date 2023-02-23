package model;

import java.util.ArrayList;
/*** A class representing a list of sessions.
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */
public class SessionList
{
  private ArrayList<Session> sessionList;

  /**
   * a constructor for a SessionList class that initialises Session arrayList
   */
  public SessionList()
  {
    this.sessionList = new ArrayList<>();
  }

  /**
   * a getter method that gives a session by an inputted date and if there are no sessions for that date it throws an illegal argument exception
   * @param dateOfLesson from which the program receives the dateOfLesson in a MyDate format
   * @return a Session type variable
   */
  public Session getSessionByDate(MyDate dateOfLesson)
  {
    for (int i = 0; i < sessionList.size(); i++)
    {
      if (dateOfLesson.equals(sessionList.get(i).getSessionDate()))
      {
        return sessionList.get(i);
      }
    }
    throw new IllegalArgumentException("There are no sessions for this course on that day.");
  }

  /**
   * a method that gives the size of the sessionList
   * @return an in type variable
   */
  public int size()
  {
    return sessionList.size();
  }

  /**
   * a getter method for Session that returns a session by its index
   * @param index from which the program receives the index in an int format
   * @return a Session type variable
   */
  public Session getSession(int index)
  {
    return sessionList.get(index);
  }

  /**
   * a method to add a session to the SessionList
   * @param session from which the program receives the session in a Session format
   */
  public void add(Session session)
  {
    this.sessionList.add(session);
  }

  /**
   * a remove method that removes a specific session from the sessionList
   * @param session from which the program receives the session in a Session format
   */
  public void remove(Session session)
  {
    this.sessionList.remove(session);
  }


  /**
   * a toString method to return instance variable sessionList as a string
   * @return a string type variable
   */
  @Override public String toString()
  {
    return "SessionList{" + "sessionList=" + sessionList + '}';
  }

  /**
   * A default copy method for SessionList.
   * @return SessionList object that is the same as "this" SessionList.
   */
  public SessionList copy()
  {
    SessionList returnThis = new SessionList();
    for (int i = 0; i < this.size(); i++)
    {
      returnThis.add(this.getSession(i));
    }
    return returnThis;
  }
}
