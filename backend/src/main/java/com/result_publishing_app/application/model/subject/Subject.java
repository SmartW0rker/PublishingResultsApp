package com.result_publishing_app.application.model.subject;

import com.result_publishing_app.application.model.SemesterEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @Column(name = "id", unique = true)
    private String id;

    @Column(name="name",nullable = false)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private SemesterEnum semester;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return Objects.equals(getName(), subject.getName()) && getSemester() == subject.getSemester();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSemester());
    }
}
