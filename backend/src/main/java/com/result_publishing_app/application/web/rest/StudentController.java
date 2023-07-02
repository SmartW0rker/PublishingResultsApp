package com.result_publishing_app.application.web.rest;

import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.exceptions.StudentExistException;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/{index}")
    public Student findStudentByIndex(@PathVariable String index){
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

    @DeleteMapping("/delete/{index}")
    public void deleteStudent(@PathVariable String index){
        studentService.delete(index);
    }

    @PostMapping(value = "/import-csv", consumes = "multipart/form-data")
    @ApiOperation(value = "Import CSV data", consumes = "multipart/form-data")
    public ResponseEntity<String> importCsv(@ApiParam(value = "CSV file to import", required = true)
                                            @RequestParam("file") MultipartFile file) {
        try {
            studentService.importCsv(file);
            return ResponseEntity.ok("CSV file imported successfully");
        } catch (IOException | CsvValidationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import CSV file: " + e.getMessage());
        }
    }

}
