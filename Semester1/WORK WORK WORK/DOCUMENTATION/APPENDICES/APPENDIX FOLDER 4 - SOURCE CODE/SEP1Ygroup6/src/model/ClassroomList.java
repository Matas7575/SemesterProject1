package model;

import java.util.ArrayList;

/**
 * A class that provides a classroom list
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class ClassroomList {

    private ArrayList<Classroom> classroomList;

    /**
     * A zero-argument constructor intializing the ArrayList
     */

    public ClassroomList() {
        this.classroomList = new ArrayList<>();
    }

    /**
     * A get method for the list which gets the classroom located at position index
     * @param index the index
     * @return the classroom from the position index
     */

    public Classroom getClassroom(int index) { //2 time units
        return classroomList.get(index); //2 time units
    }

    /**
     * A add classroom method which adds a new classroom to the list
     * @param classroom the classroom added
     */

    public void addClassroom(Classroom classroom) {
        classroomList.add(classroom);
    }

    /**
     * A remove classroom method which removes the specific classroom from the list
     * @param classroom the classroom that will be removed
     */

    public void removeClassroom(Classroom classroom) {
        classroomList.remove(classroom);
    }

    /**
     * A size method which return the size of the list
     * @return the size of the list
     */

    public int size() {
        return classroomList.size();
    }


    /**
     * A toString method which creates a string of all the variables from the class
     * @return the elements inside the ArrayList
     */

    @Override
    public String toString() {
        return "ClassroomList{" + "classroomList=" + classroomList + '}';
    }
}


