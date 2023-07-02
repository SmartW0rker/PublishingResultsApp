package com.result_publishing_app.application.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.result_publishing_app.application.exceptions.*;
import com.result_publishing_app.application.mapper.CourseMapper;
import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.course.CourseCommand;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professorRole.ProfessorRole;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public Course findById(String id){
        return courseRepository.findById(id).orElseThrow(()->
                new CourseNotFoundException(String.format("Student with id %s not found",id)));
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public List<Course> findByYear(String year){
        return courseRepository.findByYear(year);
    }

    public Course create(CourseCommand command){
        if (courseRepository.existsById(command.getId()))
            throw new CourseExistException(String.format("Course with id %s already exist",command.getId()));
        else
            return courseRepository.save(courseMapper.commandToModel(command));
    }

    @Transactional
    public Course update(CourseCommand command){
        Course course=courseRepository.findById(command.getId()).orElseThrow(()->
                new CourseNotFoundException(String.format("Course with id %s not found",command.getId())));

        courseMapper.updateCourse(command,course);


        List<Professor> professors=professorRepository.findAllById(command.getProfessorIds());
        List<Student> students=studentRepository.findAllById(command.getStudentIndexes());
        List<Session> sessions=sessionRepository.findAllById(command.getSessionNames());

        professors.forEach(professor -> professor.getCourses().add(course));

        students.forEach(student -> student.getCourses().add(course));

        sessions.forEach(session -> session.getCourses().add(course));

        professorRepository.saveAll(professors);
        studentRepository.saveAll(students);
        sessionRepository.saveAll(sessions);

        return courseRepository.save(course);
    }

    @Transactional
    public void delete(String id){
        Course course=courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException(String.
                format("Course with id %s does not exist",id)));

        List<Professor> professors=professorRepository.findByCourses_Id(id);
        List<Student> students=studentRepository.findByCourses_Id(id);
        List<Session> sessions=sessionRepository.findByCourses_Id(id);

        professors.forEach(professor -> professor.getCourses().remove(course));

        students.forEach(student -> student.getCourses().remove(course));

        sessions.forEach(session -> session.getCourses().remove(course));

        professorRepository.saveAll(professors);
        studentRepository.saveAll(students);
        sessionRepository.saveAll(sessions);

        courseRepository.delete(course);
    }

    @Transactional
    public void importCsvStudentsToCourse(MultipartFile file) throws CsvValidationException, IOException {
        CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));

        String[] line;
        HashMap<String,Student> studentsMap=new HashMap<>();
        HashMap<String,Course> courseMap=new HashMap<>();

        csvReader.readNext();

        while ((line = csvReader.readNext()) != null) {
            mapAndAddStudentToCourse(line, studentsMap, courseMap);
        }
        studentRepository.saveAll(studentsMap.values());
    }

    public void importCsvProfessorsToCourse(MultipartFile file) throws CsvValidationException, IOException {
        CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));

        String[] line;
        HashMap<String,Professor> professorsMap=new HashMap<>();
        HashMap<String,Course> courseMap=new HashMap<>();

        csvReader.readNext();

        while ((line = csvReader.readNext()) != null) {
            mapAndAddProfessorToCourse(line, professorsMap, courseMap);
        }
        professorRepository.saveAll(professorsMap.values());
    }

    private void mapAndAddProfessorToCourse(String[] line, HashMap<String, Professor> professorsMap, HashMap<String, Course> courseMap) {
        Course course;
        Professor professor;
        String courseId= line[0];
        String professorId= line[1];

        if (!courseMap.containsKey(courseId))
            courseMap.put(courseId, courseRepository.findById(courseId).orElseThrow(
                    () -> new CourseNotFoundException(String.format("Course with id %s does not exist", courseId))));

        course= courseMap.get(courseId);

        if (!professorsMap.containsKey(professorId))
            professorsMap.put(professorId,professorRepository.findById(professorId).orElseThrow(
                    ()->new ProfessorNotFoundException(String.format("Professor with id %s does not exist",professorId))
            ));

        professor= professorsMap.get(professorId);

        professor.getCourses().add(course);
    }

    private void mapAndAddStudentToCourse(String[] line, HashMap<String, Student> studentsMap, HashMap<String, Course> courseMap) {
        Course course;
        Student student;
        String courseId= line[0];
        String studentIndex= line[1];

        if (!courseMap.containsKey(courseId))
            courseMap.put(courseId, courseRepository.findById(courseId).orElseThrow(
                    () -> new CourseNotFoundException(String.format("Course with id %s does not exist", courseId))));

        course= courseMap.get(courseId);

        if (!studentsMap.containsKey(studentIndex))
            studentsMap.put(studentIndex,studentRepository.findByIndex(studentIndex).orElseThrow(
                    ()->new ProfessorNotFoundException(String.format("Student with index %s does not exist",studentIndex))
            ));

        student= studentsMap.get(studentIndex);

        student.getCourses().add(course);
    }
}
