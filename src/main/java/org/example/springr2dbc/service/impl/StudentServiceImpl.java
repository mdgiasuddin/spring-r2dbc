package org.example.springr2dbc.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springr2dbc.model.projection.StudentPojo;
import org.example.springr2dbc.repository.StudentRepository;
import org.example.springr2dbc.service.StudentService;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final DatabaseClient databaseClient;

    @Override
    public Flux<StudentPojo> getAllStudents() {

        String query = "select s.id as s_id, s.name as s_name, u.id as u_id, u.name as u_name from student s inner join university u on s.university_id = u.id order by s.id";

        return databaseClient.sql(query)
                .fetch()
                .all()
                .map(this::processStudent);
    }

    private StudentPojo processStudent(Map<String, Object> map) {
        Integer id = (Integer) map.get("s_id");
        String name = (String) map.get("s_name");
        Integer universityId = (Integer) map.get("u_id");
        String universityName = (String) map.get("u_name");

        return new StudentPojo(id, name, universityId, universityName);
    }
}
