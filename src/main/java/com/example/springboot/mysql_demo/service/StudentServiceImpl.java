package com.example.springboot.mysql_demo.service;

import com.example.springboot.mysql_demo.converter.StudentConverter;
import com.example.springboot.mysql_demo.dao.Student;
import com.example.springboot.mysql_demo.dao.StudentRepository;
import com.example.springboot.mysql_demo.dto.StudentDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO getStudentById(long id) {
        return StudentConverter.convertStudentToStudentDTO(studentRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public List<StudentDTO> getStudents() {
        return studentRepository.findAll().stream().map(StudentConverter::convertStudentToStudentDTO).toList();
    }

    @Override
    public long addNewStudent(StudentDTO studentDTO) {
        List<Student> studentList = studentRepository.findByEmail(studentDTO.getEmail());
        if (!studentList.isEmpty()) {
            throw new IllegalArgumentException("email: " + studentDTO.getEmail() + " already exists");
        }
        Student student = studentRepository.save(StudentConverter.convertStudentDTOToStudent(studentDTO));
        return student.getId();
    }

    @Override
    public void deleteStudentById(long id) {
        studentRepository.findById(id).orElseThrow(RuntimeException::new);
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudentById(long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        if (StringUtils.isNotBlank(name) && !student.getName().equals(name)) {
            student.setName(name);
        }

        if (StringUtils.isNotBlank(email) && !student.getEmail().equals(email)) {
            student.setEmail(email);
        }

        return StudentConverter.convertStudentToStudentDTO(studentRepository.save(student));
    }
}
