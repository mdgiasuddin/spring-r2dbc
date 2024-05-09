package org.example.springr2dbc.service;

import org.example.springr2dbc.model.projection.StudentPojo;
import reactor.core.publisher.Flux;

public interface StudentService {
    Flux<StudentPojo> getAllStudents();
}
