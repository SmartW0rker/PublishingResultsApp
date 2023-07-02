package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,String> {
    List<Subject> findByName(String name);

    @Override
    boolean existsById(String s);
}
