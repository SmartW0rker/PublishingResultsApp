package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    Optional<Student> findByIndex(String index);
    Optional<Student> findByEmail(String email);
    boolean existsByIndex(String index);
    boolean existsByEmail(String email);

    List<Student> findByCourses_Id(String id);
}
