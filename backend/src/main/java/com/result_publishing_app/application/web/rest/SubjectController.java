package com.result_publishing_app.application.web.rest;

import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.model.subject.SubjectCommand;
import com.result_publishing_app.application.service.SubjectService;
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
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/{id}")
    public Subject findById(@PathVariable String id){
        return subjectService.findById(id);
    }

    @GetMapping("/findByName")
    public List<Subject> findByName(@RequestParam String name){
        return subjectService.findByName(name);
    }

    @GetMapping("/")
    public List<Subject> findAll(){
        return subjectService.findAll();
    }

    @PostMapping("/create")
    public Subject create(@RequestBody SubjectCommand command){
        return subjectService.create(command);
    }

    @PutMapping("/update")
    public Subject update(@RequestBody SubjectCommand command){
        return subjectService.update(command);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        subjectService.delete(id);
    }

    @PostMapping(value = "/import-csv", consumes = "multipart/form-data")
    @ApiOperation(value = "Import CSV data", consumes = "multipart/form-data")
    public ResponseEntity<String> importCsv(@ApiParam(value = "CSV file to import", required = true)
                                            @RequestParam("file") MultipartFile file) {
        try {
            subjectService.importCsv(file);
            return ResponseEntity.ok("CSV file imported successfully");
        } catch (IOException | CsvValidationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import CSV file: " + e.getMessage());
        }
    }
}
