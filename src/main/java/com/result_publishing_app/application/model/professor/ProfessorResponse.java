package com.result_publishing_app.application.model.professor;

import com.result_publishing_app.application.model.professorRole.ProfessorRole;
import com.result_publishing_app.application.model.subject.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessorResponse {

    private long id;

    private String name;

    private String email;

    private ProfessorRole role;

    private List<Long> subjectIds;
}
