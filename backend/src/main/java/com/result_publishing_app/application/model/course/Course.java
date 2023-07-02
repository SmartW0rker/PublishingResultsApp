package com.result_publishing_app.application.model.course;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import com.result_publishing_app.application.model.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "year", length = 4)
    private String year;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @JsonManagedReference
    @ManyToMany(mappedBy = "courses")
    private Set<Professor> professors;

    @JsonManagedReference
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @JsonManagedReference
    @ManyToMany(mappedBy = "courses")
    private Set<Session> sessions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(getYear(), course.getYear()) && Objects.equals(getSubject(), course.getSubject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getYear(), getSubject());
    }
}
