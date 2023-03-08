package com.result_publishing_app.application.service;

import com.result_publishing_app.application.exceptions.SubjectExistException;
import com.result_publishing_app.application.exceptions.SubjectNotFoundException;
import com.result_publishing_app.application.mapper.SubjectMapper;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.model.subject.SubjectCommand;
import com.result_publishing_app.application.model.subject.SubjectResponse;
import com.result_publishing_app.application.repository.ProfessorRepository;
import com.result_publishing_app.application.repository.SessionRepository;
import com.result_publishing_app.application.repository.StudentRepository;
import com.result_publishing_app.application.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        List<Session> sessions = new ArrayList<>();
        List<Professor> professors = new ArrayList<>();
        List<Student> students=new ArrayList<>();

        if (command.getSessionIds()!=null) 
            sessions=sessionRepository.findAllById(command.getSessionIds());

        if (command.getProfessorIds()!=null)
            professors=professorRepository.findAllById(command.getProfessorIds());

        if (command.getStudentIds()!=null)
            students=studentRepository.findAllById(command.getStudentIds());

        subjectRepository.save(subject);

        sessions.forEach(s->{
            s.getSubjects().add(subject);
        });

        professors.forEach(p->{
                p.getSubjects().add(subject);
        });

        students.forEach(student->{
                student.getSubjects().add(subject);

        });

        sessionRepository.saveAll(sessions);
        professorRepository.saveAll(professors);
        studentRepository.saveAll(students);

        return subject;
    }

    public void delete(Long id){
        Subject subject= subjectRepository.findById(id).orElseThrow(()->new SubjectNotFoundException(String
                .format("Subject with id %d does not exist",id)));

        List<Professor> professors=professorRepository.findBySubjects_Id(id);
        List<Student> students=studentRepository.findBySubjects_Id(id);
        List<Session> sessions=sessionRepository.findBySubjects_Id(id);

        professors.forEach(p->{
            p.getSubjects().remove(subject);
        });

        students.forEach(student -> {
            student.getSubjects().remove(subject);
        });

        sessions.forEach(session -> {
            session.getSubjects().remove(subject);
        });

        professorRepository.saveAll(professors);
        studentRepository.saveAll(students);
        sessionRepository.saveAll(sessions);

        subjectRepository.delete(subject);
    }
}
