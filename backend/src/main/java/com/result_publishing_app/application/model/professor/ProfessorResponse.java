package com.result_publishing_app.application.model.professor;

import com.result_publishing_app.application.model.professorRole.ProfessorRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessorResponse {

    private String id;

    private String name;

    private String email;

    private ProfessorRole role;

    private List<String> courseIds;
}
