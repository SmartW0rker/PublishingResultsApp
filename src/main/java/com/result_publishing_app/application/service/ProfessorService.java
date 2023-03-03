package com.result_publishing_app.application.service;

import com.result_publishing_app.application.exceptions.ProfessorEmailExist;
import com.result_publishing_app.application.exceptions.ProfessorNotFoundException;
import com.result_publishing_app.application.mapper.ProfessorMapper;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professor.ProfessorCommand;
import com.result_publishing_app.application.model.professorRole.ProfessorRole;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ProfessorMapper mapper;

    public Professor findById(Long id){
        return professorRepository.findById(id).orElseThrow(()-> new ProfessorNotFoundException(id));
    }

    public List<Professor> findAll(){
        return professorRepository.findAll();
    }

    public Professor findByEmail(String email){
        return professorRepository.findByEmail(email).orElse(null);
    }

    public void deleteById(Long id){
        professorRepository.deleteById(id);
    }

    public Professor createProfessor(ProfessorCommand command){

        if (professorRepository.existsByEmail(command.getEmail()))
            throw new ProfessorEmailExist(command.getEmail());
        else{
            Professor professor=mapper.createProfessor(command);
            return professorRepository.save(professor);
        }
    }

    public Professor updateProfessor(ProfessorCommand command){

        Professor professor=professorRepository.findById(command.getId()).orElseThrow(()-> new ProfessorNotFoundException(command.getId()));

        mapper.updateProfessor(command,professor);

        professorRepository.save(professor);

        return professor;

    }
}
