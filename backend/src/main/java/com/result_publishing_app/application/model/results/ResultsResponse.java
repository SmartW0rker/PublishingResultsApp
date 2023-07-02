package com.result_publishing_app.application.model.results;

import com.result_publishing_app.application.model.course.Course;
import com.result_publishing_app.application.model.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultsResponse {

    private long id;

    private Session session;

    private Course course;

    private String pdf;
}
