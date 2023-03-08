package com.result_publishing_app.application.model.professor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.result_publishing_app.application.model.professorRole.ProfessorRole;
import com.result_publishing_app.application.model.subject.Subject;
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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_sequence")
    @SequenceGenerator(name = "professor_sequence", sequenceName = "professor_sequence", initialValue = 1, allocationSize = 3)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private ProfessorRole role;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "professor_subject",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor professor)) return false;
        return getName().equals(professor.getName()) && getEmail().equals(professor.getEmail()) && getRole() == professor.getRole() && Objects.equals(getSubjects(), professor.getSubjects());
    }

}
