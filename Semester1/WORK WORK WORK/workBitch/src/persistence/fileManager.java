package persistence;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

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
  public AllCoursesList loadAllCourses(String filename)
  {
    AllCoursesList allCourses = new AllCoursesList(); // placeholder courseList with all the courses from the file
    try
    {
      File file = new File(filename);
      Scanner in = new Scanner(file);

      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        Course course = new Course(token[2].trim(), Integer.parseInt(token[0].trim()), token[1].trim(), Integer.parseInt(token[4].trim()), new Teacher(token[3].trim()));
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

      HashSet<String> classesXYZ = new HashSet<>();

      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        Course course = new Course(token[2].trim(), Integer.parseInt(token[0].trim()), token[1].trim(), Integer.parseInt(token[4].trim()), new Teacher(token[3].trim()));
        allCourses.addCourse(course);
      }

        for (int i = 0; i < allCourses.size(); i++)
        {
          classesXYZ.add(allCourses.getCourse(i).getClassFull());
        }

      System.out.println("Existing classes:" + classesXYZ);

        // creating listOfCoursesForEachClass with empty courseLists
        for (String turma : classesXYZ)
        {
          String localSemester = "";
          localSemester+= turma.charAt(0);
          StringBuilder localClass = new StringBuilder();
          for (int i = 1; i < turma.length(); i++)
          {
            localClass.append(turma.charAt(i));
          }
          CourseList classCourseList = new CourseList(Integer.parseInt(localSemester), localClass.toString());
          listOfCoursesForEachClass.addCourseList(classCourseList);
        }

        for (int i = 0; i < allCourses.size(); i++)
        {
          CourseList thisCourseList = listOfCoursesForEachClass.getCourseListByClass(allCourses.getCourse(i).getSemester(), allCourses.getCourse(i).getClassxyz());
          thisCourseList.addCourse(allCourses.getCourse(i));
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
      Student student = new Student(token[2].trim(),token[3].trim(),Integer.parseInt(token[0].trim()),token[1].trim());
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
        if (!(teacherList.getTeachers().contains(teacherList.getTeacher(token[3].trim()))))
        {
          teacherList.addTeacher(new Teacher(token[3].trim()));
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
