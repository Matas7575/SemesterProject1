package model;

/**
 * A class that provides a course
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class Course
{
    private String title;
    private int semester;
    private String classxyz;
    private int ectsPoints;
    private Teacher teacher;
    private SessionList sessionList;

    /**
     * A five-parameter constructor initializing all the variables
     * @param title the title
     * @param semester the semester
     * @param classxyz the class
     * @param ectsPoints the ectsPoints
     * @param teacher the teacher
     */

    public Course(String title, int semester, String classxyz, int ectsPoints, Teacher teacher)
    {
        this.title = title;
        this.semester = semester;
        this.classxyz = classxyz;
        this.ectsPoints = ectsPoints;
        this.teacher = teacher;
        this.sessionList = new SessionList();
    }

    /**
     * A get method which gets the title of the course
     * @return the title
     */

    public String getTitle()
    {
        return title;
    }

    /**
     * A get method which gets the semester in which the course is
     * @return the semester
     */

    public int getSemester()
    {
        return semester;
    }

    /**
     * A get method which gets the classxyz variable
     * @return the classxyz
     */

    public String getClassxyz()
    {
        return classxyz;
    }

    /**
     * A get method which gets the exact class in which the course is being taught
     * @return the semester and the classxyz combined in a String
     */

    public String getClassFull()
    {
        return semester + "" + classxyz;
    }

    /**
     * A get method for the ectsPoints variable
     * @return the ectsPoints
     */

    public int getEctsPoints()
    {
        return ectsPoints;
    }

    /**
     * A get method for sessionList variable
     * @return the sessionList
     */

    public SessionList getSessionList() {
        return sessionList;
}

    /**
     * A add method for the session
     * @param session the session
     */

    public void addSession(Session session)
    {
        this.sessionList.add(session);
    }

    /**
     * A cancel method for the session which deletes the session
     * @param session the session you want to delete
     */

    public void cancelSession(Session session) { this.sessionList.remove(session);}

    /**
     * A toString method printing out all the variables in the class
     * @return a String containing all the variables from the class
     */

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", semester=" + semester +
                ", classxyz=" + classxyz +
                ", ectsPoints=" + ectsPoints +
                ", teacher=" + teacher +
                '}';
    }
}
