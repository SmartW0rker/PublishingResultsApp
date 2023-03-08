package com.result_publishing_app.application.model.session;

import com.result_publishing_app.application.model.subject.Subject;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse {
    private long Id;

    private String name;

    private LocalDate dueDate;

    private List<Long> subjectIds;
}
