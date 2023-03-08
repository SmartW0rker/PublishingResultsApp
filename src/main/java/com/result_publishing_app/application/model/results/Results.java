package com.result_publishing_app.application.model.results;

import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.subject.Subject;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "join_sequence")
    @SequenceGenerator(name = "join_sequence", sequenceName = "join_sequence", allocationSize = 1)
    @Column(name = "id",unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name="link_pdf")
    private String link;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Results)) return false;
        Results results = (Results) o;
        return Objects.equals(getSession(), results.getSession()) && Objects.equals(getSubject(), results.getSubject()) && Objects.equals(getLink(), results.getLink());
    }
}
