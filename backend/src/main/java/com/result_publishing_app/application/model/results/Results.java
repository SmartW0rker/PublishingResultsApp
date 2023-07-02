package com.result_publishing_app.application.model.results;

import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.session.Session;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "results_sequence")
    @SequenceGenerator(name = "results_sequence", sequenceName = "results_sequence", allocationSize = 1)
    @Column(name = "id",unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_name")
    private Session session;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(columnDefinition = "bytea",name="pdf")
    private byte[] pdfBytes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Results)) return false;
        Results results = (Results) o;
        return Objects.equals(getId(), results.getId()) && Objects.equals(getSession(), results.getSession()) && Objects.equals(getCourse(), results.getCourse()) && Arrays.equals(getPdfBytes(), results.getPdfBytes());
    }
}
