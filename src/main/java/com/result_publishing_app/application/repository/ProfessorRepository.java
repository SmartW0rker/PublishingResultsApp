package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {

    List<Professor> findByName(String name);

    Optional<Professor> findByEmail(String email);
    Boolean existsByEmail(String email);

    List<Professor> findBySubjects_Id(Long id);
}
