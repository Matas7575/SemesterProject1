package model;

import java.util.ArrayList;

/**
 * A class that provides a list of courses
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class CourseList
{
    private int semester;
    private String classxyz;
    private boolean isPersonal;
    private String personalViaID;
    private ArrayList<Course> courses;

    /**
     * A two-argument constructor which is used to initialize the variables
     * @param semester the semester number
     * @param classxyz the class name
     */

    public CourseList(int semester, String classxyz)
    {
        this.semester = semester;
        this.classxyz = classxyz;
        this.courses = new ArrayList<>();
        this.isPersonal = false;
        this.personalViaID = null;
    }

    /**
     * A add method which adds a course to the CourseList
     * @param course the course object to be added
     */

    public void addCourse(Course course)
    {
        courses.add(course);
    }

    /**
     * A remove method which removes a course from the CourseList
     * @param course the course object to be removed
     */

    public void removeCourse(Course course)
    {
        courses.remove(course);
    }

    /**
     * A get method which gets a course based on its title
     * @param title the title
     * @return the course which has the title equal to the parameter
     */

    public Course getCourse(String title)
    {
        for (int i = 0; i < courses.size(); i++)
        {
            if (title.equals(courses.get(i).getTitle()))
            {
                return courses.get(i);
            }
        }
        return null;
    }

    /**
     * A get method which gets a course located at the index position in the CourseList
     * @param index the index
     * @return the course located at that index
     */

    public Course getCourse(int index)
    {
        return courses.get(index);
    }

    /**
     * A get method which gets the semester of the CourseList
     * @return the semester in which this CourseList belongs to
     */

    public int getSemester()
    {
        return semester;
    }

    /**
     * A get method which gets the class where  this CourseList belongs to
     * @return the class name
     */

    public String getClassxyz()
    {
        return classxyz;
    }

    /**
     * A get method that gets the name of the class combined with the semester, providing a more specific name for the class from which the CourseList belongs to
     * @return the semester plus the classxyz combined into a String
     */

    public String getClassFull() { // 1 time units
        return semester + "" + classxyz; // 1 time units
    }

    /**
     * A get method that gets the personal via ID
     * @return the personal VIA ID
     */

    public String getPersonalViaID() // I added a new method that wasn't in astah
    {
        return personalViaID;
    }

    /**
     * A method that returns the size of the CourseList
     * @return the size
     */

    public int size()
    {
        return courses.size();
    }

    /**
     * A copy method that creates a new object identical to the one stored in the class
     * @return the new object
     */

    public CourseList copy()
    {
        return new CourseList(semester,classxyz);
    }

    /**
     * A method that makes the CourseList personal and sets the isPersonal variable to true
     */

    public void makePersonal()
    {
        this.isPersonal = true;
    }

    /**
     * A method that requires a ViaID String and assigns it to the personalViaID
     * @param viaID the ViaID
     */

    public void setPersonalViaID(String viaID)
    {
        this.personalViaID = viaID;
    }

    /**
     * A toString method that prints every variable in the class
     * @return a String line containing the variables and the values stored in them
     */

    @Override
    public String toString() {
        return "CourseList{" +
                "semester=" + semester +
                ", classxyz=" + classxyz +
                ", isPersonal=" + isPersonal +
                ", personalViaID='" + personalViaID + '\'' +
                ", courses=" + courses +
                '}';
    }
}
