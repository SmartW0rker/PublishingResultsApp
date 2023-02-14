package com.result_publishing_app.application.model.student;

import com.result_publishing_app.application.model.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private long id;
    @Column(name = "index", length = 6)
    private String index;
    @Column(name = "email")
    private String email;
    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getId() == student.getId() && Objects.equals(getIndex(), student.getIndex()) && Objects.equals(getEmail(), student.getEmail()) && Objects.equals(getSubjects(), student.getSubjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIndex(), getEmail(), getSubjects());
    }
}
