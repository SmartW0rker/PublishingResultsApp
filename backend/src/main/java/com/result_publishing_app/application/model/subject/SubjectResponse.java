package com.result_publishing_app.application.model.subject;

import com.result_publishing_app.application.model.SemesterEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectResponse {

    private String id;

    private String name;

    private SemesterEnum semester;
}
