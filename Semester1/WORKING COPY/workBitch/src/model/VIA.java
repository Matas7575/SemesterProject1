package model;

import parser.ParserException;
import parser.XmlJsonParser;
import persistence.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * A class which implements the interface
 * @author Laura Rebelo, Matas Armonaitis, Dragos Bonaparte, Daniel Adriao Lopes
 * @version 1.0 - December 2021
 */

public class VIA implements VIAModel {
    private fileManager fileManager;
    private ClassroomList allClassrooms;
    private TeacherList allTeachers;
    private StudentList allStudents;
    private CourseListList allCourseLists;
    private AllCoursesList allCourses;

    /**
     * A zero-argument constructor which initializes all the variables
     */

    public VIA() {
        this.fileManager = new fileManager();
        this.allClassrooms = fileManager.loadClassrooms("src/Rooms.txt");
        this.allStudents = fileManager.loadStudents("src/Students.txt");
        this.allCourses = fileManager.loadAllCourses("src/Courses.txt");
        this.allTeachers = fileManager.loadTeachersFromCourseFile("src/Courses.txt");
        this.allCourseLists = fileManager.loadCourseLists("src/Courses.txt");
        assignAllStudentsToTheirCourseList();
        exportClassOptionsForWebsite();
    }

    /**
     * A void method which exports all the session data that was edited in the program into a xml file according to the semester and the classxyz
     * @param semester the semester
     * @param classxyz the classxyz
     */

    public void exportClassSessionList(int semester, String classxyz)
    {
        XmlJsonParser parser = new XmlJsonParser();
        SessionList desiredSessionList = new SessionList();
        CourseList classCourseList = this.allCourseLists.getCourseListByClass(semester,classxyz);
        for (int i = 0; i < classCourseList.size(); i++)
        {
            for (int j = 0 ; j < classCourseList.getCourse(i).getSessionList().size(); j++)
            {
                Session s = classCourseList.getCourse(i).getSessionList().getSession(j);
                desiredSessionList.add(s);
            }
        }

        SessionList desiredSessionListTemp = desiredSessionList.copy();
        SessionList desiredSessionListSorted = new SessionList();

        //sort desiredSessionList
        for (int j = 0; j < desiredSessionList.size(); j++)
        {
            Session earliestSession = desiredSessionListTemp.getSession(0);
            for (int i = 1; i < desiredSessionListTemp.size(); i++)
            {
                if (desiredSessionListTemp.getSession(i).isBefore(earliestSession))
                {
                    earliestSession = desiredSessionListTemp.getSession(i);
                }
            }
            desiredSessionListSorted.add(earliestSession);
            desiredSessionListTemp.remove(earliestSession);
        }

        String filename = "../website/src/";
        String classname = semester + "" + classxyz;
        filename+= classname;
        filename += ".xml";
        System.out.println(filename);
        try {
            File file = parser.toXml(desiredSessionListSorted, filename);

        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * A void method which is used to change a specific student's class into another class
     * @param student the student of whom's you want to change the class
     * @param semester the semester of the new class
     * @param classxyz the classxyz where we are gonna move the student
     */

    public void switchStudentFromClass(Student student, int semester, String classxyz)
    {
        CourseList newCourseList = this.allCourseLists.getCourseListByClass(semester,classxyz);
        student.setCourseList(newCourseList);
        student.setSemester(semester);
        student.setClassxyz(classxyz);
    }

    /**
     * A VIA type method which loads all the information from the json file which acts as a database for the aplication
     * @return a VIA object containing all the information from the json file
     */

    @Override
    public VIA loadInfoFromFile() {
        VIA viaObject = null;
        XmlJsonParser parser = new XmlJsonParser();
        try {
            viaObject = parser.fromJson("src/persistence/via.json", VIA.class);

        } catch (ParserException e) {
            e.printStackTrace();
        }
        return viaObject;
    }

    /**
     * A save method which saves the current state of the program into the json file, so that after the program closes the user can continue from where he left
     * @param viaObject the VIA object which will be used to override the json file
     */

    @Override
    public void saveCurrentChanges(VIAModel viaObject) {
        XmlJsonParser parser = new XmlJsonParser();
        try {
            File file = parser.toJson(viaObject, "src/persistence/via.json");

        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * A void method which assigns students to their course lists
     */

    @Override
    public void assignAllStudentsToTheirCourseList() {
        for (int i = 0; i < allStudents.size(); i++) { // n+2 time units
            Student currentStudent = allStudents.getStudent(i); // 3 units
            for (int j = 0; j < allCourseLists.size(); j++) { // n+2 time units
                CourseList currentCourseList = allCourseLists.getCourseList(j); // 3 time units
                if (currentStudent.getClassFull().equals(currentCourseList.getClassFull())) { //n+2 time units https://stackoverflow.com/questions/22030798/runtime-complexity-of-string-equals-in-java
                    currentStudent.setCourseList(currentCourseList); //1 time unit
                    break; //1 time unit
                }
            }
        }
        //O(n^3)
    }

    /**
     * A void method which assigns a specific student to a course list
     * @param student the student
     */

    @Override
    public void assignStudentToCourseList(Student student) {
        for (int j = 0; j < allCourseLists.size(); j++) {
            CourseList currentCourseList = allCourseLists.getCourseList(j);
            if (student.getClassFull().equals(currentCourseList.getClassFull())) {
                student.setCourseList(currentCourseList);
                break;
            }
        }
    }

    /**
     * A void method which creates a session for a class
     * @param course the course which willl be scheduled
     * @param numberOfLessons the number of lessons the course will have
     * @param startTime the start time of the session
     * @param sessionDate the date when the session will take place
     * @param classroom the classroom where the session will take place
     */

    @Override
    public void scheduleSession(Course course, int numberOfLessons, MyTime startTime, MyDate sessionDate, Classroom classroom) {
        if (course != null && numberOfLessons != 0 && startTime != null && sessionDate != null && classroom != null)
        {
            Session newSession = new Session(course.getTitle(), numberOfLessons, startTime, sessionDate, classroom);
            course.addSession(newSession);
            allCourseLists.getCourseListByClass(course.getSemester(), course.getClassxyz()).getCourse(course.getTitle()).addSession(newSession);
            classroom.bookClassroom(sessionDate,startTime,numberOfLessons);
        }
    }

     @Override public void rescheduleSession(Session session, MyDate newDate, MyTime newTime, int numberOfLessons, Classroom newClassroom)
    {
        MyDate oldDate = session.getSessionDate();
        Classroom oldClassroom = session.getClassroom();
        MyTime oldStartTime = session.getStartTime();
        int oldNumLessons = session.getNumberOfLessons();
        if (newClassroom == null)
        {
            throw new IllegalArgumentException("Please pick a classroom.");
        }
        if (newDate.isBefore(new MyDate()))
        {
            throw new IllegalArgumentException("Do not schedule a session in the past.");
        }
        session.getClassroom().unbookClassroom(oldDate,oldStartTime,oldNumLessons);
        for (int i = 0; i < allClassrooms.size(); i++)
            if (session.getClassroom().equals(allClassrooms.getClassroom(i)))
            {
                allClassrooms.getClassroom(i).unbookClassroom(oldDate,oldStartTime,oldNumLessons);
            }
        session.setClassroom(newClassroom);
        newClassroom.bookClassroom(newDate, newTime, numberOfLessons);
        session.setSessionDate(newDate);
        session.setStartTime(newTime);
        session.setNumberOfLessons(numberOfLessons);
    }

    /**
     * Void method to schedule a session for a given class.
     *
     * @param session Session to be scheduled
     * @param semester Semester of a class for which the session is being scheduled
     * @param classxyz "CHAR" of a class (e.g., X,Y,Z) for which the session is being scheduled
     */
    @Override
    public void scheduleSession(Session session, int semester, String classxyz) {
            if (session.getSessionDate().isBefore(new MyDate()))
            {
                throw new IllegalArgumentException("Do not schedule a session in the past.");
            }

            if (session.getClassroom() == null)
            {
                throw new IllegalArgumentException("Please pick a classroom.");
            }

            //needs to go through every course of the course list, and through every session of each course to check for overlaps.
            for (int i = 0; i < this.getAllCourseLists().getCourseListByClass(semester,classxyz).size(); i++)
            {
                for (int j = 0; j < this.getAllCourseLists().getCourseListByClass(semester,classxyz).getCourse(i).getSessionList().size(); j++)
                {
                    if (this.getAllCourseLists().getCourseListByClass(semester,classxyz).getCourse(i).getSessionList().size() != 0)
                    {
                        Session check = this.getAllCourseLists()
                            .getCourseListByClass(semester, classxyz).getCourse(i).getSessionList().getSession(j);
                        if (this.checkForTimeOverlaps(session, check))
                        {
                            throw new IllegalArgumentException("Time overlap with: " + check);
                        }
                    }
                }
            }

            //needs to through every timeframe in which the classroom is booked & check if it doesn't overlap.
        DateAndTimeFrame sessionTimeFrame = new DateAndTimeFrame(
            session.getSessionDate(), session.getStartTime(),
            session.getNumberOfLessons());
        for (int i =0; i < session.getClassroom().getTimeframesBooked().size(); i++)
        {
            if (sessionTimeFrame.checkForOverlaps(session.getClassroom().getTimeframesBooked().get(i)))
            {
                throw new IllegalArgumentException("The session in " + session.getSessionDate() + " was not scheduled because the selected classroom was not available.");
            }
        }

            allCourseLists.getCourseListByClass(semester, classxyz).getCourse(session.getCourseTitle()).addSession(session);
            session.getClassroom().bookClassroom(session.getSessionDate(),
                session.getStartTime(), session.getNumberOfLessons());
        }




    /**
     * A void method which adds a student to a class
     * @param viaID the viaID of that student
     * @param semester the semester in which the class is
     * @param classxyz the class where to place the student into
     */

    @Override
    public void addStudentToClass(String viaID, int semester, String classxyz) {
        CourseList desiredClassCourseList = allCourseLists.getCourseListByClass(semester, classxyz);
        Student desiredStudent = allStudents.getStudent(viaID);
        desiredStudent.setCourseList(desiredClassCourseList);
        desiredStudent.setClassxyz(classxyz);
        desiredStudent.setSemester(semester);
    }

    /**
     * A void method removing a student from a class using the viaID
     * @param viaID the viaID of the student
     */

    @Override
    public void removeStudentFromClass(String viaID) {
        Student desiredStudent = allStudents.getStudent(viaID);
        desiredStudent.setCourseList(null);
    }

    /**
     * A void method which adds a student to a specific course so that the student can be scheduled for a session in that course
     * @param viaID the viaId of the student
     * @param course the course which we are adding the student to
     */

    @Override
    public void addStudentToCourse(String viaID, Course course) {
        Student desiredStudent = allStudents.getStudent(viaID);
        CourseList currentCourseList = desiredStudent.getCourseList();
        CourseList personalCourselist = currentCourseList.copy();
        personalCourselist.addCourse(course);
        personalCourselist.makePersonal();
        personalCourselist.setPersonalViaID(viaID);
    }

    /**
     * A void method which removes a student from a specific course
     * @param viaID the viaID used to find the student that we want to remove
     * @param course the course that where are removing the student from
     */

    @Override
    public void removeStudentFromCourse(String viaID, Course course) {
        Student desiredStudent = allStudents.getStudent(viaID);
        CourseList currentCourseList = desiredStudent.getCourseList();
        CourseList personalCourselist = currentCourseList.copy();
        personalCourselist.removeCourse(course);
        personalCourselist.makePersonal();
        personalCourselist.setPersonalViaID(viaID);
    }

    /**
     * A boolean method which checks the time for overlaps between sessions
     * @param session1 the first session
     * @param session2 the second session
     * @return true if they overlap
     * false if they are not overlapping
     */

    @Override
    public boolean checkForTimeOverlaps(Session session1, Session session2) {
        return session1.checkForTimeOverlaps(session2);
    }

    /**
     * A ArrayList method which gets all the available classrooms in that specific time space
     * @param date the date
     * @param startTime the start time
     * @param numberOfLessons the number of lessons
     * @return the list of classrooms that match those requirements
     */

    public ArrayList<Classroom> returnAvailableClassrooms(MyDate date, MyTime startTime, int numberOfLessons) //
    {
        DateAndTimeFrame trynaBook = new DateAndTimeFrame(date,startTime,numberOfLessons);  // 4 time units
        ArrayList<Classroom> availableClassrooms = new ArrayList<>(); //1 time unit
        //for every classrooms, check every "isbooked" item and check for
        // overlaps with the current timeframe tryna be booked.
        for (int i = 0; i < allClassrooms.size(); i++) // n time units
        {
            boolean isAvailable = true; // 1 time unit
            for (int j = 0; j < allClassrooms.getClassroom(i).getTimeframesBooked().size(); j++) //n time units
            {
                DateAndTimeFrame isThisBooked = allClassrooms.getClassroom(i).getTimeframesBooked().get(j); //1 time unit
                if (trynaBook.checkForOverlaps(isThisBooked)) //25 time units
                {
                    isAvailable = false; //1 time unit
                }
            }
            if (isAvailable) // 1 time unit
            {
                availableClassrooms.add(allClassrooms.getClassroom(i)); //3 time units
            }
        }
        return availableClassrooms; // 1 time unit

        //O(n^2) is the worst case because there are nested loops that both run n time units
    }

    /**
     * A StudentList method which gets the student list
     * @return all the students
     */

    @Override
    public StudentList getStudentList() {
        return allStudents;
    }

    /**
     * A TeacherList method which gets the teacher list
     * @return all the teachers
     */

    @Override
    public TeacherList getTeacherList() {
        return allTeachers;
    }

    /**
     * A ClassroomList method which gets all the classrooms
     * @return all the classrooms
     */

    @Override
    public ClassroomList getClassroomList() {
        return allClassrooms;
    }

    /**
     * A CourseListList method which gets all the course lists
     * @return all the course lists
     */

    @Override
    public CourseListList getAllCourseLists() {
        return allCourseLists;
    }

    /**
     * A AllCoursesList method which gets all the courses
     * @return all the courses
     */

    @Override
    public AllCoursesList getAllCourses() {
        return allCourses;
    }

    public void exportClassOptionsForWebsite()
    {
        File file = new File("../website/src/classxyz.xml");
        try
        {
            PrintWriter out = new PrintWriter(file);

            String xml = "";
            xml += "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
            xml += "<Classs>";
            xml += "\n";
            xml += "<Class>";
            xml += "\n";
            xml += "<ClassName>Choose a Class</ClassName>";
            xml += "\n";
            xml += "</Class>";
            for (int i = 0; i < allCourseLists.size(); i++)
            {
                xml += "<Class>";
                xml += "\n";
                xml += "<ClassName>";
                xml += allCourseLists.getCourseList(i).getClassFull();
                xml += "</ClassName>";
                xml += "\n";
                xml += "</Class>";
                xml += "\n";
            }
            xml += "</Classs>";
            out.println(xml);
            out.flush();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}