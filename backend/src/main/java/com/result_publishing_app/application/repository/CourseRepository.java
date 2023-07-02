package com.result_publishing_app.application.repository;

import com.result_publishing_app.application.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

    List<Course> findByYear(String year);
}
