package org.example.springr2dbc.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springr2dbc.model.dto.response.StudentResponse;
import org.example.springr2dbc.repository.StudentRepository;
import org.example.springr2dbc.repository.custom.CustomStudentRepository;
import org.example.springr2dbc.service.StudentService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CustomStudentRepository customStudentRepository;

    @Override
    public Flux<StudentResponse> getAllStudents() {
        return customStudentRepository.getAllStudents();
    }
}
