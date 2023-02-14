package com.result_publishing_app.application.model.subject;

import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "year",nullable = false, length = 4)
    private String year;

    @ManyToMany(mappedBy = "subjects")
    private List<Professor> professors;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

    @ManyToMany(mappedBy = "subjects")
    private List<Session> sessions;

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return getId() == subject.getId() && Objects.equals(getName(), subject.getName()) && Objects.equals(getYear(), subject.getYear()) && Objects.equals(getProfessors(), subject.getProfessors()) && Objects.equals(getStudents(), subject.getStudents()) && Objects.equals(getSessions(), subject.getSessions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getYear(), getProfessors(), getStudents(), getSessions());
    }
}
