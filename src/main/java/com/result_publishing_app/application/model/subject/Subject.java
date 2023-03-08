package com.result_publishing_app.application.model.subject;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "subject_sequence")
    @SequenceGenerator(name = "subject_sequence", sequenceName = "subject_sequence",initialValue = 7, allocationSize = 3)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "year",nullable = false, length = 4)
    private String year;

    @JsonManagedReference
    @ManyToMany(mappedBy = "subjects")
    private Set<Professor> professors;

    @JsonManagedReference
    @ManyToMany(mappedBy = "subjects")
    private Set<Student> students;

    @JsonManagedReference
    @ManyToMany(mappedBy = "subjects")
    private Set<Session> sessions;

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(getName(), subject.getName()) && Objects.equals(getYear(), subject.getYear()) && Objects.equals(getProfessors(), subject.getProfessors()) && Objects.equals(getStudents(), subject.getStudents()) && Objects.equals(getSessions(), subject.getSessions());
    }
}
