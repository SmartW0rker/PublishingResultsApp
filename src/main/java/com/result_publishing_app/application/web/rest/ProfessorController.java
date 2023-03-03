package com.result_publishing_app.application.web.rest;

import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professor.ProfessorCommand;
import com.result_publishing_app.application.service.ProfessorService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
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


}
