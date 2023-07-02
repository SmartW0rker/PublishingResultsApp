package com.result_publishing_app.application.model.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    private String id;

    private String year;

    private String subjectId;

    private Set<String> professorIds;

    private Set<String> studentIndexes;

    private Set<String> sessionNames;
}
