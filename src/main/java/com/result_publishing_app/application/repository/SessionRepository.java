package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    Optional<Session> findByName(String string);

    boolean existsByDueDateAndName(LocalDate dueDate,String name);
}
