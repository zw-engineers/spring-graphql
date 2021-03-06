package com.graphql.service.student;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentByName(final String name);

    StudentDto getStudentByDegree(String degree);

    StudentDto getStudentByTutor(String tutor);
}
