package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.session.SessionCommand;
import com.result_publishing_app.application.model.session.SessionResponse;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.student.StudentCommand;
import com.result_publishing_app.application.model.student.StudentResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    Session commandToModel(SessionCommand command);
    SessionResponse modelToResponse(Session session);
    List<SessionResponse> modelToResponse(List<Session> sessions);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSession(SessionCommand command, @MappingTarget Session session);
    @Mapping(target = "id", ignore = true)
    Session createSession(SessionCommand command);
}
