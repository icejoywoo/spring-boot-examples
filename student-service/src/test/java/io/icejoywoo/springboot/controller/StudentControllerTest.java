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

    @Test
    public void testAddNewStudent() throws Exception {
        long id = 1;
        Student student = new Student();
        student.setId((int) id);
        student.setName("test");
        student.setEmail("test@test.com");
        student.setAge(20);

        StudentDTO studentDTO = StudentConverter.convertStudentToStudentDTO(student);
        when(studentService.addNewStudent(studentDTO)).thenReturn(id);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/student")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":1, \"name\":\"test\",\"email\":\"test@test.com\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(id));

        verify(studentService, times(1)).addNewStudent(studentDTO);
    }

    @Test
    public void testDeleteStudentById() throws Exception {
        long id = 1;

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(String.format("/student/%d", id))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudentById(id);
    }

    @Test
    public void testUpdateStudentById() throws Exception {
        long id = 1;

        StudentDTO ret = new StudentDTO();
        ret.setId(id);
        ret.setName("foo");
        ret.setEmail("foo@123.com");
        when(studentService.updateStudentById(id, "foo", "foo@123.com")).thenReturn(ret);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(String.format("/student/%d", id))
                                .formField("name", "foo")
                                .formField("email", "foo@123.com")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").value(id))
                .andExpect(jsonPath("$.data.name").value("foo"))
                .andExpect(jsonPath("$.data.email").value("foo@123.com"));

        verify(studentService, times(1)).updateStudentById(id, "foo", "foo@123.com");
    }
}
