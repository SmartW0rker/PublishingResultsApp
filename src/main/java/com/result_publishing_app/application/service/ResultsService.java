package com.result_publishing_app.application.service;

import com.result_publishing_app.application.exceptions.ProfessorNotFoundException;
import com.result_publishing_app.application.exceptions.SessionNotFoundException;
import com.result_publishing_app.application.exceptions.StudentNotFoundException;
import com.result_publishing_app.application.exceptions.SubjectNotFoundException;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.results.Results;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultsService {

    @Autowired
    ResultsRepository resultsRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfessorRepository professorRepository;

    public List<Results> findBySession(Long sessionId){

        Session session=sessionRepository.findById(sessionId).orElseThrow(()->new SessionNotFoundException(String
                .format("Session with id %d does not exist",sessionId)));

        return resultsRepository.findBySession(session);
    }

    public List<Results> findBySubject(Long subjectId){
        Subject subject=subjectRepository.findById(subjectId).orElseThrow(()->new SubjectNotFoundException(String
                .format("Subject with id %d does not exist",subjectId)));

        return resultsRepository.findBySubject(subject);
    }

    public List<Results> findByStudent(Long studentId){
        Student student= studentRepository.findById(studentId).orElseThrow(()->new StudentNotFoundException(String.
                format("Student with id %d does not exist",studentId)));

        return resultsRepository.findByStudent(studentId);
    }

    public List<Results> findByProfessor(Long professorId){
        Professor professor= professorRepository.findById(professorId).orElseThrow(()-> new ProfessorNotFoundException(professorId));

        return resultsRepository.findByProfessor(professorId);
    }
}
