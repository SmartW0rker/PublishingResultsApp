package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,String> {

    Optional<Session> findByName(String string);

    boolean existsByName(String name);

    boolean existsByDueDateAndName(LocalDate dueDate,String name);

    List<Session> findByCourses_Id(String id);
}
