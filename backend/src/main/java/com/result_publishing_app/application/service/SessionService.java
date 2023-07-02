package com.result_publishing_app.application.service;

import com.result_publishing_app.application.exceptions.SessionExistException;
import com.result_publishing_app.application.exceptions.SessionNotFoundException;
import com.result_publishing_app.application.mapper.SessionMapper;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.session.SessionCommand;
import com.result_publishing_app.application.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    SessionMapper sessionMapper;

    public Session findByName(String name){
        return sessionRepository.findByName(name).orElseThrow(()->new SessionNotFoundException(String
                .format("Session with id %s does not exist",name)));
    }

    public List<Session> findAll(){
        return sessionRepository.findAll();
    }

    public Session create(SessionCommand command){

        if (sessionRepository.existsByName(command.getName()))
            throw new SessionExistException(String.
                    format("Session with name %s exists",command.getDueDate().toString(),
                            command.getName()));
        else
            return sessionRepository.save(sessionMapper.commandToModel(command));
    }

    public Session update(SessionCommand command){

        Session session= sessionRepository.findByName(command.getName()).orElseThrow(()->new SessionNotFoundException(String
                .format("Session with name %s does not exist",command.getName())));
        sessionMapper.updateSession(command,session);

        return sessionRepository.save(session);

    }

    public void  delete(String name){
        Session session= sessionRepository.findByName(name).orElseThrow(()->new SessionNotFoundException(String
                .format("Session with name %s does not exist",name)));
        sessionRepository.delete(session);
    }
}
