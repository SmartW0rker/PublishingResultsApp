package com.result_publishing_app.application.model.subject;

import com.result_publishing_app.application.model.professor.Professor;
import com.result_publishing_app.application.model.session.Session;
import com.result_publishing_app.application.model.student.Student;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponse {

    private long id;

    private String name;

    private String year;

    private List<Professor> professors;

    private List<Student> students;

    private List<Session> sessions;
}
