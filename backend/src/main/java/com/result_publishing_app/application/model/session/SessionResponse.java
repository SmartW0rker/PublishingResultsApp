package com.result_publishing_app.application.model.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse {

    private String name;

    private LocalDate dueDate;

    private List<String> courseIds;
}
