package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.exceptions.SubjectNotFoundException;
import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.course.CourseCommand;
import com.result_publishing_app.application.model.course.CourseResponse;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.repository.ProfessorRepository;
import com.result_publishing_app.application.repository.SessionRepository;
import com.result_publishing_app.application.repository.StudentRepository;
import com.result_publishing_app.application.repository.SubjectRepository;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SessionRepository sessionRepository;

    public Course commandToModel(CourseCommand command){
        if ( command == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( command.getId() );
        course.setYear( command.getYear() );

        Subject subject=subjectRepository.findById(command.getSubjectId()).orElseThrow(()-> new SubjectNotFoundException(String.
                format("Subject with id %s does not exist",command.getId()))
        );
        course.setSubject(subject);

        if (command.getProfessorIds()!=null) {
            List<Professor> professors = professorRepository.findAllById(command.getProfessorIds());
            course.setProfessors(new HashSet<>(professors));
        }

        if (command.getStudentIndexes()!=null){
            List<Student> students=studentRepository.findAllById(command.getStudentIndexes());
            course.setStudents(new HashSet<>(students));
        }

        if (command.getSessionNames()!=null){
            List<Session> sessions=sessionRepository.findAllById(command.getSessionNames());
            course.setSessions(new HashSet<>(sessions));
        }

        return course;
    }

    public CourseResponse modelToResponse(Course course){
        if ( course == null ) {
            return null;
        }

        CourseResponse courseResponse = new CourseResponse();

        courseResponse.setId(course.getId());
        courseResponse.setYear(course.getYear());
        courseResponse.setSubjectId(course.getSubject().getId());

        if (course.getProfessors()!=null){
            courseResponse.setProfessorIds(course.getProfessors().stream()
                    .map(Professor::getId).collect(Collectors.toSet()));
        }

        if (course.getStudents()!=null){
            courseResponse.setStudentIndexes(course.getStudents().stream()
                    .map(Student::getIndex).collect(Collectors.toSet()));
        }

        if (course.getSessions()!=null){
            courseResponse.setSessionNames(course.getSessions().stream()
                    .map(Session::getName).collect(Collectors.toSet()));
        }

        return courseResponse;
    }

    List<CourseResponse> modelToResponse(List<Course> courses){
        if ( courses == null ) {
            return null;
        }

        List<CourseResponse> list = new ArrayList<>( courses.size() );
        for ( Course course : courses ) {
            list.add( modelToResponse(course) );
        }

        return list;
    }

    public void updateCourse(CourseCommand command, @MappingTarget Course course){
        if ( command == null ) {
            return;
        }
        Subject subject=subjectRepository.findById(command.getSubjectId()).orElseThrow(()-> new SubjectNotFoundException(String.
                format("Subject with id %s does not exist",command.getId()))
        );

        course.setYear( command.getYear() );

        course.setSubject( subject );

        if (command.getProfessorIds()!=null) {
            List<Professor> professors = professorRepository.findAllById(command.getProfessorIds());
            course.setProfessors(new HashSet<>(professors));
        }

        if (command.getStudentIndexes()!=null){
            List<Student> students=studentRepository.findAllById(command.getStudentIndexes());
            course.setStudents(new HashSet<>(students));
        }

        if (command.getSessionNames()!=null){
            List<Session> sessions=sessionRepository.findAllById(command.getSessionNames());
            course.setSessions(new HashSet<>(sessions));
        }
    }
}
