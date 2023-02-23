package persistence;
import model.*;

public interface fileManagerModel
{
  public ClassroomList loadClassrooms(String filename);
  public CourseListList loadCourseLists(String filename);
  public AllCoursesList loadAllCourses(String filename);
  public StudentList loadStudents(String filename);
  public TeacherList loadTeachersFromCourseFile(String filename);
}
