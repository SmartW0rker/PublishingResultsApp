package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.model.student.StudentResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student commandToModel(StudentCommand command);
    StudentResponse modelToResponse(Student student);
    List<StudentResponse> modelToResponse(List<Student> students);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudent(StudentCommand command, @MappingTarget Student student);
    @Mapping(target = "id", ignore = true)
    Student createStudent(StudentCommand command);
}
