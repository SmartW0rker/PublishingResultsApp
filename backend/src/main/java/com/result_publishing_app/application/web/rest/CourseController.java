package com.result_publishing_app.application.web.rest;

import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.course.CourseCommand;
import com.result_publishing_app.application.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/")
    public List<Course> findALl(){
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Course findById(@PathVariable String id){
        return courseService.findById(id);
    }

    @GetMapping("/findByYear")
    public List<Course> findByYear(@RequestParam String year){
        return courseService.findByYear(year);
    }

    @PostMapping("/create")
    public Course create(@RequestBody CourseCommand command){
        return courseService.create(command);
    }

    @PutMapping("/update")
    public Course update(@RequestBody CourseCommand command){
        return courseService.update(command);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        courseService.delete(id);
    }

    @PostMapping(value = "/import-csv-students-to-course", consumes = "multipart/form-data")
    @ApiOperation(value = "Import CSV data", consumes = "multipart/form-data")
    public ResponseEntity<String> importCsvStudentsToCourse(@ApiParam(value = "CSV file to import", required = true)
                                            @RequestParam("file") MultipartFile file) {
        try {
            courseService.importCsvStudentsToCourse(file);
            return ResponseEntity.ok("CSV file imported successfully");
        } catch (IOException | CsvValidationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import CSV file: " + e.getMessage());
        }
    }

    @PostMapping(value = "/import-csv-professors-to-course", consumes = "multipart/form-data")
    @ApiOperation(value = "Import CSV data", consumes = "multipart/form-data")
    public ResponseEntity<String> importCsvProfessorsToCourse(@ApiParam(value = "CSV file to import", required = true)
                                            @RequestParam("file") MultipartFile file) {
        try {
            courseService.importCsvProfessorsToCourse(file);
            return ResponseEntity.ok("CSV file imported successfully");
        } catch (IOException | CsvValidationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import CSV file: " + e.getMessage());
        }
    }
}
