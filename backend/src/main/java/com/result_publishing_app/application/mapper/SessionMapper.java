package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.session.SessionCommand;
import com.result_publishing_app.application.model.session.SessionResponse;
import com.result_publishing_app.application.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionMapper {

    @Autowired
    CourseRepository courseRepository;

    public Session commandToModel(SessionCommand command) {
        if ( command == null ) {
            return null;
        }

        Session session = new Session();

        session.setName( command.getName() );
        session.setDueDate( command.getDueDate() );
        if (command.getCourseIds()!=null){
            List<Course> courses=courseRepository.findAllById(command.getCourseIds());
            session.setCourses(new HashSet<>(courses));
        }


        return session;
    }

    public SessionResponse modelToResponse(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionResponse sessionResponse = new SessionResponse();

        sessionResponse.setName( session.getName() );
        sessionResponse.setDueDate( session.getDueDate() );
        sessionResponse.setCourseIds(session.getCourses().stream().map(Course::getId).collect(Collectors.toList()));
        return sessionResponse;
    }

    public List<SessionResponse> modelToResponse(List<Session> sessions) {
        if ( sessions == null ) {
            return null;
        }

        List<SessionResponse> list = new ArrayList<>( sessions.size() );
        for ( Session session : sessions ) {
            list.add( modelToResponse( session ) );
        }

        return list;
    }

    public void updateSession(SessionCommand command, Session session) {
        if ( command == null ) {
            return;
        }

        session.setName( command.getName() );
        session.setDueDate( command.getDueDate() );

        if (command.getCourseIds()!=null){
            List<Course> courses=courseRepository.findAllById(command.getCourseIds());
            session.setCourses(new HashSet<>(courses));
        }
    }
}
