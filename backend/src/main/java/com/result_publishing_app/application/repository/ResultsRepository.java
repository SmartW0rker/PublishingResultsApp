package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.results.Results;
import com.result_publishing_app.application.model.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultsRepository extends JpaRepository<Results,Long> {

    List<Results> findBySession(Session session);

    List<Results> findByCourse(Course course);

    @Query(value = "SELECT r.id,r.course_id,r.session_name,r.pdf FROM results as r " +
            "JOIN professor_course as pc on pc.course_id = r.course_id " +
            "JOIN professor as p on p.id = pc.professor_id " +
            "WHERE p.id=:professorId",nativeQuery = true)
    List<Results> findByProfessor(@Param("professorId") String professorId);

    @Query(value = "SELECT r.id,r.course_id,r.session_name,r.pdf FROM results as r " +
            "JOIN student_course as sc on r.course_id = sc.course_id " +
            "JOIN student as s on s.index = sc.student_index " +
            "WHERE s.index=:index",nativeQuery = true)
    List<Results> findByStudent(@Param("index") String index);
}
