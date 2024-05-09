package org.example.springr2dbc.repository;

import org.example.springr2dbc.model.entity.Student;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface StudentRepository extends R2dbcRepository<Student, Integer> {

}
