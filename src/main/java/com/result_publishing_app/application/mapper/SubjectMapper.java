package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.model.student.StudentResponse;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.model.subject.SubjectCommand;
import com.result_publishing_app.application.model.subject.SubjectResponse;
import com.result_publishing_app.application.repository.ProfessorRepository;
import com.result_publishing_app.application.service.SubjectService;
import jakarta.persistence.EntityManager;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = SubjectService.class)
public interface SubjectMapper {

    Subject commandToModel(SubjectCommand command);
    SubjectResponse modelToResponse(Subject subject);
    List<SubjectResponse> modelToResponse(List<Subject> subjects);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "professors", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    void updateSubject(SubjectCommand command, @MappingTarget Subject subject);
    @Mapping(target = "id", ignore = true)
    Subject createSubject(SubjectCommand command);
}
