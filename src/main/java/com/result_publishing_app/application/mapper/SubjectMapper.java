package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.model.student.StudentResponse;
import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.model.subject.SubjectCommand;
import com.result_publishing_app.application.model.subject.SubjectResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject commandToModel(SubjectCommand command);
    SubjectResponse modelToResponse(Subject subject);
    List<SubjectResponse> modelToResponse(List<Subject> subjects);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubject(SubjectCommand command, @MappingTarget Subject subject);
    @Mapping(target = "id", ignore = true)
    Subject createSubject(SubjectCommand command);
}
