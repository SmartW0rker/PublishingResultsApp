package com.result_publishing_app.application.service;

import com.result_publishing_app.application.exceptions.CourseNotFoundException;
import com.result_publishing_app.application.exceptions.ProfessorNotFoundException;
import com.result_publishing_app.application.exceptions.SessionNotFoundException;
import com.result_publishing_app.application.exceptions.StudentNotFoundException;
import com.result_publishing_app.application.mapper.ResultsMapper;
import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.results.Results;
import com.result_publishing_app.application.model.results.ResultsResponse;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ResultsService {

    @Autowired
    ResultsRepository resultsRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ResultsMapper resultsMapper;

    public List<ResultsResponse> findBySession(String sessionName){

        Session session=sessionRepository.findByName(sessionName).orElseThrow(()->new SessionNotFoundException(String
                .format("Session with id %s does not exist",sessionName)));

        return resultsMapper.modelToResponse(resultsRepository.findBySession(session));
    }

    public List<ResultsResponse> findByCourse(String courseId){
        Course course=courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException(String
                .format("Course with id %s does not exist",courseId)));

        return resultsMapper.modelToResponse(resultsRepository.findByCourse(course));
    }

    public List<ResultsResponse> findByStudent(String index){
        Student student= studentRepository.findById(index).orElseThrow(()->new StudentNotFoundException(String.
                format("Student with id %s does not exist",index)));

        return resultsMapper.modelToResponse(resultsRepository.findByStudent(index));
    }

    public List<ResultsResponse> findByProfessor(String professorId){
        Professor professor= professorRepository.findById(professorId).orElseThrow(()-> new ProfessorNotFoundException(professorId));

        return resultsMapper.modelToResponse(resultsRepository.findByProfessor(professorId));
    }

    public void createResultWithPdf(MultipartFile pdfFile, String courseId, String sessionName) throws IOException {

        Session session=sessionRepository.findByName(sessionName).orElseThrow(()->new SessionNotFoundException(String
                .format("Session with id %s does not exist",sessionName)));

        Course course=courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException(String
                .format("Course with id %s does not exist",courseId)));

        byte[] pdfBytes = pdfFile.getBytes();

        Results results=new Results();

        results.setCourse(course);
        results.setSession(session);
        results.setPdfBytes(pdfBytes);

        resultsRepository.save(results);
    }
}
