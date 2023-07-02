package com.result_publishing_app.application.web.rest;

import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professor.ProfessorCommand;
import com.result_publishing_app.application.service.ProfessorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public Professor findProfessor(@PathVariable String id){
        return professorService.findById(id);
    }

    @GetMapping("/")
    public List<Professor> findAllProfessors(){
        return professorService.findAll();
    }

    @GetMapping("/findByEmail")
    public Professor findProfessorByEmail(@RequestParam String email){
        return professorService.findByEmail(email);
    }

    @PostMapping("/create")
    public Professor createProfessor(@RequestBody ProfessorCommand command){
        return professorService.createProfessor(command);
    }

    @PutMapping("/update")
    public Professor updateProfessor(@RequestBody ProfessorCommand command){
        return professorService.updateProfessor(command);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        professorService.deleteProfessor(id);
    }

    @PostMapping(value = "/import-csv", consumes = "multipart/form-data")
    @ApiOperation(value = "Import CSV data", consumes = "multipart/form-data")
    public ResponseEntity<String> importCsv(@ApiParam(value = "CSV file to import", required = true)
                                                @RequestParam("file") MultipartFile file) {
        try {
            professorService.importCsv(file);
            return ResponseEntity.ok("CSV file imported successfully");
        } catch (IOException | CsvValidationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import CSV file: " + e.getMessage());
        }
    }


}
