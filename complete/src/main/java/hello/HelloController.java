package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/apps")
public class HelloController {

  @Autowired
  StudentService studentService;

  @RequestMapping("/hello")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping(value = "/students", method = RequestMethod.POST)
  public ResponseEntity addStudent(@RequestBody Student student) throws URISyntaxException {
    studentService.addStudent(student);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(new URI("/apps/students/"+student.getId()));
    return new ResponseEntity(httpHeaders, HttpStatus.ACCEPTED);

  }

  @RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET)
  public Student getStudent(@PathVariable("studentId") long id) {
    Student s1 = studentService.getStudent(id);
    return s1;
  }




}
