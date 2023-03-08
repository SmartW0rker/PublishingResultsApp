package com.result_publishing_app.application.model.student;

import com.result_publishing_app.application.model.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponse {

    private long id;

    private String index;

    private String email;

    private List<Long> subjectIds;
}
