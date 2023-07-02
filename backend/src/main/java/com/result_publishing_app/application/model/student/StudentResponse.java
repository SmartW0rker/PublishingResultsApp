package com.result_publishing_app.application.model.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponse {

    private String index;

    private String email;

    private List<String> courseIds;
}
