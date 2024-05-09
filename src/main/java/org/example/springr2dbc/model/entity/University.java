package org.example.springr2dbc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "university")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class University {

    @Id
    private int id;

    private String name;
}
