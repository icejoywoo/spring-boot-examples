package io.icejoywoo.springboot.service;

import io.icejoywoo.springboot.converter.StudentConverter;
import io.icejoywoo.springboot.dao.Student;
import io.icejoywoo.springboot.dao.StudentRepository;
import io.icejoywoo.springboot.dto.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testGetStudentById() {
        long id = 1;
        Student student = new Student();
        student.setId((int) id);
        student.setName("test");
        student.setEmail("test@test.com");
        student.setAge(20);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        StudentDTO result = studentService.getStudentById(id);
        assertThat(result).isEqualTo(StudentConverter.convertStudentToStudentDTO(student));

        verify(studentRepository, times(1)).findById(id);
    }
}
