package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.model.student.StudentResponse;
import com.result_publishing_app.application.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    @Autowired
    CourseRepository courseRepository;

    public Student commandToModel(StudentCommand command) {
        if ( command == null ) {
            return null;
        }

        Student student = new Student();

        student.setIndex( command.getIndex() );
        student.setEmail( command.getEmail() );

        if (command.getCourseIds()!=null){
            student.setCourses(new HashSet<>(courseRepository.findAllById(command.getCourseIds())));
        }


        return student;
    }

    public StudentResponse modelToResponse(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setIndex( student.getIndex() );
        studentResponse.setEmail( student.getEmail() );

        if (student.getCourses()!=null){
            studentResponse.setCourseIds(student.getCourses().stream()
                    .map(Course::getId).collect(Collectors.toList()));
        }

        return studentResponse;
    }

    public List<StudentResponse> modelToResponse(List<Student> students) {
        if ( students == null ) {
            return null;
        }

        List<StudentResponse> list = new ArrayList<>( students.size() );
        for ( Student student : students ) {
            list.add( modelToResponse( student ) );
        }

        return list;
    }

    public void updateStudent(StudentCommand command, Student student) {
        if ( command == null ) {
            return;
        }

        student.setEmail( command.getEmail() );

        if (command.getCourseIds()!=null){
            student.setCourses(new HashSet<>(courseRepository.findAllById(command.getCourseIds())));
        }
    }
}
