package io.icejoywoo.springboot.controller;

import io.icejoywoo.springboot.converter.StudentConverter;
import io.icejoywoo.springboot.dao.Student;
import io.icejoywoo.springboot.dto.StudentDTO;
import io.icejoywoo.springboot.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetStudentById() throws Exception {
        long id = 1;
        Student student = new Student();
        student.setId((int) id);
        student.setName("test");
        student.setEmail("test@test.com");
        student.setAge(20);

        StudentDTO studentDTO = StudentConverter.convertStudentToStudentDTO(student);
        when(studentService.getStudentById(id)).thenReturn(studentDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/student/%d", id)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").value(id))
                .andExpect(jsonPath("$.data.name").value(student.getName()))
                .andExpect(jsonPath("$.data.email").value(student.getEmail()));

        verify(studentService, times(1)).getStudentById(id);
    }
}
