package model;

import java.util.ArrayList;

/**
 * A class that provides a list of all CourseList objects for each class (X,Y,Z,DK)
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class CourseListList
{
    private ArrayList<CourseList> list;


    /**
     * A non argument constructor initializing the variables
     */

    public CourseListList()
    {
        this.list = new ArrayList<>();
    }

    /**
     * A CourseList method that searches for a course list using only the class of the course and throws an exception if there is not course with that semester and class.
     * @param semester the semester
     * @param classxyz the class
     * @return the course list of that class (X,Y,Z,DK)
     */

    public CourseList getCourseListByClass(int semester, String classxyz)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getSemester() == semester && list.get(i).getClassxyz().equals(classxyz))
            {
                return list.get(i);
            }
        }
        throw new IllegalArgumentException("There is no course with this semester and class");
    }

    /**
     * A CourseList method which gets a course list using an index
     * @param index the index where to look for the CourseList object
     * @return the CourseList object found at that specific index
     */

    public CourseList getCourseList(int index) // 2 time units
    {
        return list.get(index); // 2 time units
    }

    /**
     * A CourseList method which gets a course list using VIAID
     * throws an exception if nothing is found using that ViaID
     * @param ViaID the ViaID used for searching the list
     * @return the CourseList object found using the ViaID
     */

    public CourseList getPersonalCourseListByViaID(String ViaID)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getPersonalViaID().equals(ViaID))
            {
                return list.get(i);
            }
        }
        throw new IllegalArgumentException("This Via user either doesn't exist or doesn't have a personal CourseList.");
    }

    /**
     * A add method which adds a CourseList object to the list
     * @param courseList the CourseList object
     */

    public void addCourseList(CourseList courseList)
    {
        this.list.add(courseList);
    }

    /**
     * A method that gets the size of the list
     * @return the integer value of the size of the list
     */

    public int size() // 2 time units
    {
        return list.size(); // 2 time units
    }

    /**
     * A toString method which prints all the variables of the class
     * @return the list in a String form
     */

    @Override public String toString()
    {
        return "CourseListList{" + "list=" + list + '}';
    }
}
