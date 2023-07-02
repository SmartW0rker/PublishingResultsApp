package com.result_publishing_app.application.mapper;

import com.result_publishing_app.application.model.subject.Subject;
import com.result_publishing_app.application.model.subject.SubjectCommand;
import com.result_publishing_app.application.model.subject.SubjectResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectMapper {
    public Subject commandToModel(SubjectCommand command) {
        if ( command == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setId( command.getId() );
        subject.setName( command.getName() );
        subject.setSemester( command.getSemester() );

        return subject;
    }

    public SubjectResponse modelToResponse(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        SubjectResponse subjectResponse = new SubjectResponse();

        subjectResponse.setId( subject.getId() );
        subjectResponse.setName( subject.getName() );
        subjectResponse.setSemester( subject.getSemester() );

        return subjectResponse;
    }

    public List<SubjectResponse> modelToResponse(List<Subject> subjects) {
        if ( subjects == null ) {
            return null;
        }

        List<SubjectResponse> list = new ArrayList<>( subjects.size() );
        for ( Subject subject : subjects ) {
            list.add( modelToResponse( subject ) );
        }

        return list;
    }

    public void updateSubject(SubjectCommand command, Subject subject) {
        if ( command == null ) {
            return;
        }
        subject.setName( command.getName() );
        subject.setSemester( command.getSemester() );
    }
}
