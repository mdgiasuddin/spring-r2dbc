package org.example.springr2dbc.controller;

import lombok.RequiredArgsConstructor;
import org.example.springr2dbc.model.dto.response.StudentResponse;
import org.example.springr2dbc.service.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Validated
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Flux<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }
}
