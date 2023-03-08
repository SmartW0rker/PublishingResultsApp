package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.results.Results;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ResultsRepository extends JpaRepository<Results,Integer> {

    List<Results> findBySession(Session session);

    List<Results> findBySubject(Subject subject);

    @Query(value = "SELECT * FROM results as r " +
            "JOIN professor_subject ps on ps.subject_id = r.subject_id " +
            "JOIN professor p on p.id = ps.professor_id " +
            "WHERE p.id=:professorId",nativeQuery = true)
    List<Results> findByProfessor(@Param("professorId") Long id);

    @Query(value = "SELECT * FROM results as r " +
            "JOIN student_subject ss on r.subject_id = ss.subject_id " +
            "JOIN student s on s.id = ss.student_id " +
            "WHERE s.id=:studentId",nativeQuery = true)
    List<Results> findByStudent(@Param("studentId") Long id);
}
