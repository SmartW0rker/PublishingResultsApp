package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    List<Subject> findByName(String name);

    List<Subject> findAllByYear(String year);

    boolean existsByNameAndYear(String name,String year);
}
