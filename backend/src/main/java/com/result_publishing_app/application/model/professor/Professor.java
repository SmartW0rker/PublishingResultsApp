package com.result_publishing_app.application.model.professor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.professorRole.ProfessorRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private ProfessorRole role;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "professor_course",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(getName(), professor.getName()) && Objects.equals(getEmail(), professor.getEmail()) && getRole() == professor.getRole() && Objects.equals(getCourses(), professor.getCourses());
    }
}
