package com.result_publishing_app.application.model.session;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.result_publishing_app.application.model.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "due_date")
    private LocalDate dueDate;

    @JsonBackReference
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "results",
            joinColumns = @JoinColumn(name = "session_name"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return Objects.equals(getName(), session.getName()) && Objects.equals(getDueDate(), session.getDueDate()) && Objects.equals(getCourses(), session.getCourses());
    }
}
