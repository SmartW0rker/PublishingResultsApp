package com.result_publishing_app.application.model.results;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="session_id")
    private long sessionId;

    @Column(name="subject_id")
    private long subjectId;

    @Column(name="link_pdf")
    private String link;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Results)) return false;
        Results results = (Results) o;
        return getId() == results.getId() && getSessionId() == results.getSessionId() && getSubjectId() == results.getSubjectId() && getLink().equals(results.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSessionId(), getSubjectId(), getLink());
    }
}
