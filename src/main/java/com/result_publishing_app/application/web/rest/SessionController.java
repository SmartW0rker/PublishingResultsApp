package com.result_publishing_app.application.web.rest;

import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.session.SessionCommand;
import com.result_publishing_app.application.service.SessionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    SessionService service;

    @GetMapping("/{id}")
    public Session findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/findByName")
    public Session findByName(@RequestParam String name){
        return service.findByName(name);
    }

    @GetMapping("/")
    public List<Session> findAll(){
        return service.findAll();
    }

    @PostMapping("/create")
    public Session create(@RequestBody SessionCommand command){
        return service.create(command);
    }

    @PutMapping("/update")
    public Session update(@RequestBody SessionCommand command){
        return service.update(command);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
