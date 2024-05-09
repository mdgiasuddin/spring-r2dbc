package org.example.springr2dbc.model.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentPojo {
    private Integer id;
    private String name;
    private Integer universityId;
    private String universityName;
}
