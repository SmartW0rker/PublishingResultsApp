package com.result_publishing_app.application.model.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.result_publishing_app.application.model.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

   @Id
    @Column(name = "index", length = 6)
    private String index;
    @Column(name = "email")
    private String email;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_index"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;


}
