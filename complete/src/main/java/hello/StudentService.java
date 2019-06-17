package hello;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
  private static Map<Long, Student> students = new HashMap<>();

  public StudentService() {
    Student s1 = new Student("ashish", 30);
    Student s2 = new Student("anshu", 25);
    students.put(s1.getId(), s1);
    students.put(s2.getId(), s2);
    printStudent();
  }

  public void addStudent(Student student){
    students.put(student.getId(), student);
    printStudent();
  }

  public void removeStudent(long id){
    students.remove(id);
    printStudent();
  }

  public Student getStudent(long id){
    printStudent();
    return students.get(id);
  }
  public void printStudent(){
    System.out.println("Printing Students : ");
    for(long studentId : students.keySet()){
      System.out.println(students.get(studentId));
    }
  }
}
