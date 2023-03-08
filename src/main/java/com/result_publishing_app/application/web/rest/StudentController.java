package com.result_publishing_app.application.web.rest;

import com.result_publishing_app.application.exceptions.StudentExistException;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.model.student.StudentResponse;
import com.result_publishing_app.application.service.StudentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @GetMapping("/findByIndex")
    public Student findStudentByIndex(@RequestParam String index){
        return studentService.findByIndex(index);
    }

    @GetMapping("/findByEmail")
    public Student findStudentByEmail(@RequestParam String email){
        return studentService.findByEmail(email);
    }

    @GetMapping("/")
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody StudentCommand command) throws StudentExistException {
        return studentService.create(command);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody StudentCommand command){
        return studentService.update(command);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.delete(id);
    }

}
