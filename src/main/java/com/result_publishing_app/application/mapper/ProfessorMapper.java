package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professor.ProfessorCommand;
import com.result_publishing_app.application.model.professor.ProfessorResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    Professor commandToModel(ProfessorCommand command);
    ProfessorResponse modelToResponse(Professor professor);
    List<ProfessorResponse> modelToResponse(List<Professor> professors);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfessor(ProfessorCommand command, @MappingTarget Professor professor);
    @Mapping(target = "id", ignore = true)
    Professor createProfessor(ProfessorCommand command);
}
