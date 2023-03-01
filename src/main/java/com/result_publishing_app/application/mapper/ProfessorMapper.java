package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.professor.ProfessorCommand;
import com.result_publishing_app.application.model.professor.ProfessorResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    Professor commandToModel(ProfessorCommand command);
    ProfessorResponse modelToResponse(Professor professor);
    List<ProfessorResponse> modelToResponse(List<Professor> professors);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfessor(Professor professor, @MappingTarget ProfessorCommand command);
}
