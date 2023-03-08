package com.result_publishing_app.application.web.rest;

import com.result_publishing_app.application.model.results.Results;
import com.result_publishing_app.application.service.ResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

    @Autowired
    ResultsService resultsService;

    @GetMapping("/findBySubject/{id}")
    public List<Results> findBySubject(@PathVariable Long id){
        return resultsService.findBySubject(id);
    }

    @GetMapping("/findBySession/{id}")
    public List<Results> findBySession(@PathVariable Long id){
        return resultsService.findBySession(id);
    }

    @GetMapping("/findByStudent/{id}")
    public List<Results> findByStudent(@PathVariable Long id){
        return resultsService.findByStudent(id);
    }

    @GetMapping("/findByProfessor/{id}")
    public List<Results> findByProfessor(@PathVariable long id){
        return resultsService.findByProfessor(id);
    }
}
