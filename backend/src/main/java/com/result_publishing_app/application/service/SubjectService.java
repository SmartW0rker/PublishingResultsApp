package com.result_publishing_app.application.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.exceptions.SubjectExistException;
import com.result_publishing_app.application.exceptions.SubjectNotFoundException;
import com.result_publishing_app.application.mapper.SubjectMapper;
import com.result_publishing_app.application.model.SemesterEnum;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professorRole.ProfessorRole;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.model.subject.SubjectCommand;
import com.result_publishing_app.application.repository.ProfessorRepository;
import com.result_publishing_app.application.repository.SessionRepository;
import com.result_publishing_app.application.repository.StudentRepository;
import com.result_publishing_app.application.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectMapper subjectMapper;


    public Subject findById(String id){
        return subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException(String
                .format("Subject with id %s does not exist",id)));
    }

    public List<Subject> findByName(String name){
        return subjectRepository.findByName(name);
    }

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Subject create(SubjectCommand command){
        if (subjectRepository.existsById(command.getId()))
            throw new SubjectExistException(String.
                    format("Subject with id %s already exist",command.getId()));
        else
            return subjectRepository.save(subjectMapper.commandToModel(command));
    }

    public Subject update(SubjectCommand command){
        Subject subject= subjectRepository.findById(command.getId()).orElseThrow(()->new SubjectNotFoundException(String
                .format("Subject with id %s does not exist",command.getId())));

        subjectMapper.updateSubject(command,subject);
        return subjectRepository.save(subject);
    }

    public void delete(String id){
        Subject subject= subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException(String
                .format("Subject with id %s does not exist",id)));

        subjectRepository.delete(subject);
    }

    @Transactional
    public void importCsv(MultipartFile file) throws IOException, CsvValidationException {
        CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
        String[] line;
        //Read first line because it is data with row names
        csvReader.readNext();
        while ((line = csvReader.readNext()) != null) {
            if (subjectRepository.existsById(line[0]))
                throw new SubjectExistException(String.format("Subject with id %s already exist",line[0]));
            Subject subject = new Subject();
            subject.setId(line[0]);
            subject.setName(line[1]);
            subject.setSemester(SemesterEnum.valueOf(line[2]));
            subjectRepository.save(subject);
        }
    }
}
