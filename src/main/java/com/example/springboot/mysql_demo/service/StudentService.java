package com.example.springboot.mysql_demo.service;

import com.example.springboot.mysql_demo.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO getStudentById(long id);
    List<StudentDTO> getStudents();

    long addNewStudent(StudentDTO studentDTO);

    void deleteStudentById(long id);

    StudentDTO updateStudentById(long id, String name, String email);
}
