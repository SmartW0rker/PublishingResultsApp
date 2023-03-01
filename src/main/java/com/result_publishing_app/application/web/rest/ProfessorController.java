package com.result_publishing_app.application.web.rest;

import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public Professor findProfessor(@PathVariable Long id){
        return professorService.findById(id);
    }

    @GetMapping("/")
    public List<Professor> findAllProfessors(){
        return professorService.findAll();
    }
}
