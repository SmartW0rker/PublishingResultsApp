package com.result_publishing_app.application.model.session;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.result_publishing_app.application.model.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "session_sequence")
    @SequenceGenerator(name = "session_sequence", sequenceName = "session_sequence",initialValue = 5, allocationSize = 3)
    @Column(name = "id", unique = true)
    private long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "due_date")
    private LocalDate dueDate;

    @JsonBackReference
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "results",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(getName(), session.getName()) && Objects.equals(getDueDate(), session.getDueDate()) && Objects.equals(getSubjects(), session.getSubjects());
    }
}
