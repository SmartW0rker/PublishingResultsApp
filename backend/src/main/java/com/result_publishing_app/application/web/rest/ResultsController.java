package com.result_publishing_app.application.web.rest;

import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.model.results.Results;
import com.result_publishing_app.application.model.results.ResultsResponse;
import com.result_publishing_app.application.service.ResultsService;
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
@RequestMapping("/api/results")
public class ResultsController {

    @Autowired
    ResultsService resultsService;

    @GetMapping("/findByCourse/{id}")
    public List<ResultsResponse> findByCourse(@PathVariable String id){
        return resultsService.findByCourse(id);
    }

    @GetMapping("/findBySession/{name}")
    public List<ResultsResponse> findBySession(@PathVariable String name){
        return resultsService.findBySession(name);
    }

    @GetMapping("/findByStudent/{index}")
    public List<ResultsResponse> findByStudent(@PathVariable String index){
        return resultsService.findByStudent(index);
    }

    @GetMapping("/findByProfessor/{id}")
    public List<ResultsResponse> findByProfessor(@PathVariable String id){
        return resultsService.findByProfessor(id);
    }

    @PostMapping(value = "course/{courseId}/session/{sessionName}/import-pdf", consumes = "multipart/form-data")
    @ApiOperation(value = "Import PDF data", consumes = "multipart/form-data")
    public ResponseEntity<String> importCsvProfessorsToCourse(@ApiParam(value = "PDF file to import", required = true)
                                                              @RequestParam("pdfFile") MultipartFile pdfFile,
                                                              @PathVariable String courseId,
                                                              @PathVariable String sessionName
                                                              ) {
        try {
            resultsService.createResultWithPdf(pdfFile,courseId,sessionName);
            return ResponseEntity.ok("PDF file imported successfully and results successfully created");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import PRF file or created results: " + e.getMessage());
        }
    }
}
