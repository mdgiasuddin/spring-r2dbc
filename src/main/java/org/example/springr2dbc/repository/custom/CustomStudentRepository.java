package org.example.springr2dbc.repository.custom;

import lombok.RequiredArgsConstructor;
import org.example.springr2dbc.model.dto.response.StudentResponse;
import org.example.springr2dbc.model.dto.response.UniversityResponse;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomStudentRepository {

    private final DatabaseClient databaseClient;

    public Flux<StudentResponse> getAllStudents() {

        String query = "select s.id as s_id, s.name as s_name, u.id as u_id, u.name as u_name from student s inner join university u on s.university_id = u.id order by s.id";

        return databaseClient.sql(query)
                .fetch()
                .all()
                .map(this::processStudent);
    }

    private StudentResponse processStudent(Map<String, Object> map) {
        Integer id = (Integer) map.get("s_id");
        String name = (String) map.get("s_name");
        Integer universityId = (Integer) map.get("u_id");
        String universityName = (String) map.get("u_name");

        return new StudentResponse(
                id, name,
                new UniversityResponse(universityId, universityName)
        );
    }

}
