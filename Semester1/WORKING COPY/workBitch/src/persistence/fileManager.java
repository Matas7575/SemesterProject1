package persistence;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class fileManager implements fileManagerModel
{
  public fileManager()
  {

  }
  public ClassroomList loadClassrooms(String filename)
  {
    ClassroomList classroomList = new ClassroomList();

    try
    {
      File file = new File(filename);
      Scanner in = new Scanner(file);

      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        String room = token[0].trim();
        int capacity = Integer.parseInt(token[1].trim());
        if (token.length > 2)
        {
          String counterpart = token[2].trim();

            classroomList.addClassroom(
                new ClassroomFoldable(room, capacity, counterpart));
          }
        else
          classroomList.addClassroom(new Classroom(room, capacity));
        }

      in.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    System.out.println("loaded classrooms");
    System.out.println(classroomList);
    return classroomList;
  }

  //TODO make it so that when every information of a course is similar to one except the teacher, the course has two teachers. this might be hard
  public AllCoursesList loadAllCourses(String filename)
  {
    AllCoursesList allCourses = new AllCoursesList(); // placeholder courseList with all of the courses from the file
    try
    {
      File file = new File(filename);
      Scanner in = new Scanner(file);

      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        int semester = Integer.parseInt(token[0].trim());
        String classxyz = token[1].trim();
        String title = token[2].trim();
        Teacher teacher = new Teacher(token[3].trim());
        int ectsPoints = Integer.parseInt(token[4].trim());
        Course course = new Course(title, semester, classxyz, ectsPoints,
            teacher);
        allCourses.addCourse(course);
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    System.out.println("loaded all courses.");
    System.out.println(allCourses);
    return allCourses;

  }

  public CourseListList loadCourseLists(String filename)
  {
    CourseList allCourses = new CourseList(-1,"");
    CourseListList listOfCoursesForEachClass = new CourseListList();
    try
    {
      File file = new File(filename);
      Scanner in = new Scanner(file);
      Scanner again = new Scanner(file);

      HashSet<String> classesXYZ = new HashSet<>();

      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        int semester = Integer.parseInt(token[0].trim());
        String classxyz = token[1].trim();
        String title = token[2].trim();
        Teacher teacher = new Teacher(token[3].trim());
        int ectsPoints = Integer.parseInt(token[4].trim());

        Course course = new Course(title, semester, classxyz, ectsPoints,
            teacher);
        allCourses.addCourse(course);
      }

        for (int i = 0; i < allCourses.size(); i++)
        {
          String semesterAndClass = allCourses.getCourse(i).getClassFull();
          classesXYZ.add(semesterAndClass);
        }

      System.out.println("Existing classes:" + classesXYZ);

        // creating listOfCoursesForEachClass with empty courseLists
        for (String turma : classesXYZ)
        {
          String localSemester = "";
          localSemester+= turma.charAt(0);
          int localSemesterInt = Integer.parseInt(localSemester);
          String localClass = "";
          for (int i = 1; i < turma.length(); i++)
          {
            localClass += turma.charAt(i);
          }
          CourseList classCourseList = new CourseList(localSemesterInt, localClass);
          listOfCoursesForEachClass.addCourseList(classCourseList);
        }

        /* unnefective code :) see below for faster runtime */
//        for (String turma : classesXYZ)
//        {
//          for (int i = 0; i < allCourses.size(); i++)
//          {
//            if (allCourses.getCourse(i).getClassFull().equals(turma))
//            {
//              int s = allCourses.getCourse(i).getSemester();
//              String c = allCourses.getCourse(i).getClassxyz();
//              CourseList thisCourseList = listOfCoursesForEachClass.getCourseListByClass(
//                  s, c);
//              thisCourseList.addCourse(allCourses.getCourse(i));
//            }
//          }
//        }

        for (int i = 0; i < allCourses.size(); i++)
        {
          Course currentCourse = allCourses.getCourse(i);
          int s = currentCourse.getSemester();
          String c = currentCourse.getClassxyz();
          CourseList thisCourseList = listOfCoursesForEachClass.getCourseListByClass(
              s, c);
          thisCourseList.addCourse(currentCourse);
        }
      }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    System.out.println(listOfCoursesForEachClass);
    return listOfCoursesForEachClass;
  }

  public StudentList loadStudents(String filename)
  {
  StudentList studentList = new StudentList();
    try
  {
    File file = new File(filename);
    Scanner in = new Scanner(file);

    while (in.hasNext())
    {
      String line = in.nextLine();
      String[] token = line.split(",");
      int semester = Integer.parseInt(token[0].trim());
      String classxyz = token[1].trim();
      String viaID = token[2].trim();
      String name = token[3].trim();
      Student student = new Student(viaID,name,semester,classxyz);
      studentList.addStudent(student);
    }
  }
    catch (FileNotFoundException e)
  {
    e.printStackTrace();
  }
    System.out.println("loaded students.");
    System.out.println(studentList);
    return studentList;
}
  public TeacherList loadTeachersFromCourseFile(String filename)
  {
    TeacherList teacherList = new TeacherList();
    try
    {
      File file = new File(filename);
      Scanner in = new Scanner(file);

      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        String viaID = token[3].trim();

        if (!(teacherList.getTeachers().contains(teacherList.getTeacher(viaID))))
        {
          Teacher teacher = new Teacher(viaID);
          teacherList.addTeacher(teacher);
        }

      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    System.out.println("loaded teachers.");
    System.out.println(teacherList);
    return teacherList;
  }
}
