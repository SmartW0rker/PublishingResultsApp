package com.result_publishing_app.application.model.session;

import com.result_publishing_app.application.model.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @ManyToMany
    @JoinTable(
            name = "session_subject",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return getId() == session.getId() && Objects.equals(getName(), session.getName()) && Objects.equals(getDueDate(), session.getDueDate()) && Objects.equals(getSubjects(), session.getSubjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDueDate(), getSubjects());
    }
}
