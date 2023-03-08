package com.result_publishing_app.application.model.results;

import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.subject.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultsResponse {

    private long id;

    private Session session;

    private Subject subject;

    private String link;
}
