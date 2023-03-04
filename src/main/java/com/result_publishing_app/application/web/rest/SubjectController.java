package com.result_publishing_app.application.web.rest;

import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.model.subject.SubjectCommand;
import com.result_publishing_app.application.repository.SubjectRepository;
import com.result_publishing_app.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/{id}")
    public Subject findById(@PathVariable Long id){
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
}
