package io.icejoywoo.springboot.converter;

import io.icejoywoo.springboot.dao.Student;
import io.icejoywoo.springboot.dto.StudentDTO;

public class StudentConverter {

    public static StudentDTO convertStudentToStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        return dto;
    }

    public static Student convertStudentDTOToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        return student;
    }
}
