package com.result_publishing_app.application.service;

import com.result_publishing_app.application.exceptions.SubjectExistException;
import com.result_publishing_app.application.exceptions.SubjectNotFoundException;
import com.result_publishing_app.application.mapper.SubjectMapper;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.model.subject.SubjectCommand;
import com.result_publishing_app.application.model.subject.SubjectResponse;
import com.result_publishing_app.application.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectMapper subjectMapper;


    public Subject findById(Long id){
        return subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException(String
                .format("Subject with id %d does not exist",id)));
    }

    public List<Subject> findByName(String name){
        return subjectRepository.findByName(name);
    }

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Subject create(SubjectCommand command){
        if (subjectRepository.existsByNameAndYear(command.getName(), command.getYear()))
            throw new SubjectExistException(String.
                    format("Subject with name %s and year %s year already exist",command.getName(),command.getYear() ));
        else
            return subjectRepository.save(subjectMapper.createSubject(command));
    }

    public Subject update(SubjectCommand command){
        Subject subject= subjectRepository.findById(command.getId()).orElseThrow(()->new SubjectNotFoundException(String
                .format("Subject with id %d does not exist",command.getId())));

        subjectMapper.updateSubject(command,subject);
        return subject;
    }
}
