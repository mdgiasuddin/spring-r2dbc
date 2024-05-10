package org.example.springr2dbc.service;

import org.example.springr2dbc.model.dto.response.StudentResponse;
import reactor.core.publisher.Flux;

public interface StudentService {
    Flux<StudentResponse> getAllStudents();
}
