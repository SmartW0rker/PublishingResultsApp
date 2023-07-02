package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professor.ProfessorCommand;
import com.result_publishing_app.application.model.professor.ProfessorResponse;
import com.result_publishing_app.application.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfessorMapper {

    @Autowired
    CourseRepository courseRepository;

    public Professor commandToModel(ProfessorCommand command) {
        if ( command == null ) {
            return null;
        }

        Professor professor = new Professor();

        professor.setId( command.getId() );
        professor.setName( command.getName() );
        professor.setEmail( command.getEmail() );
        professor.setRole( command.getRole() );

        if (command.getCourseIds()!=null){
            professor.setCourses(new HashSet<>(courseRepository.findAllById(command.getCourseIds())));
        }


        return professor;
    }

    public ProfessorResponse modelToResponse(Professor professor) {
        if ( professor == null ) {
            return null;
        }

        ProfessorResponse professorResponse = new ProfessorResponse();

        professorResponse.setId( professor.getId() );
        professorResponse.setName( professor.getName() );
        professorResponse.setEmail( professor.getEmail() );
        professorResponse.setRole( professor.getRole() );

        if (professor.getCourses()!=null){
            professorResponse.setCourseIds(professor.getCourses().stream()
                    .map(Course::getId).collect(Collectors.toList()));
        }

        return professorResponse;
    }

    public List<ProfessorResponse> modelToResponse(List<Professor> professors) {
        if ( professors == null ) {
            return null;
        }

        List<ProfessorResponse> list = new ArrayList<>( professors.size() );
        for ( Professor professor : professors ) {
            list.add( modelToResponse( professor ) );
        }

        return list;
    }

    public void updateProfessor(ProfessorCommand command, Professor professor) {
        if ( command == null ) {
            return;
        }

        professor.setName( command.getName() );
        professor.setEmail( command.getEmail() );
        professor.setRole( command.getRole() );

        if (command.getCourseIds()!=null){
            professor.setCourses(new HashSet<>(courseRepository.findAllById(command.getCourseIds())));
        }
    }
}
