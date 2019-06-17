package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/apps")
public class HelloController {

  @Autowired
  StudentService studentService;

  @RequestMapping("/hello")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping(value = "/students", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity addStudent(@RequestBody Student student) throws URISyntaxException {
    studentService.addStudent(student);
    HttpHeaders httpHeaders = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(student.getId())
        .toUri();

    httpHeaders.setLocation(location);
    return new ResponseEntity(location, HttpStatus.ACCEPTED);
  }

  @RequestMapping(value = "/students/{studentId}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<Student> addStudent(@PathVariable("studentId") long id,
                                            @RequestBody Student student)  {
    student.setId(id);
    studentService.addStudent(student);
    return new ResponseEntity(student, HttpStatus.OK);

  }

  @RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Student> getStudent(@PathVariable("studentId") long id) {
    Student s1 = studentService.getStudent(id);
    return new ResponseEntity<>(s1, HttpStatus.OK);
  }

  @RequestMapping(value = "/students", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public ResponseEntity<List<Student>> getStudents(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "age", required = false) Integer age) {
    List<Student> students = studentService.getStudents();
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE)
  public ResponseEntity removeStudent(@PathVariable("studentId") long id) {
    studentService.removeStudent(id);
    HttpHeaders headers = new HttpHeaders();
    return new ResponseEntity<>(headers, HttpStatus.OK);
  }




}
