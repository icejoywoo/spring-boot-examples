package com.example.springboot.mysql_demo.controller;

import com.example.springboot.mysql_demo.Response;
import com.example.springboot.mysql_demo.dto.StudentDTO;
import com.example.springboot.mysql_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public Response<StudentDTO> getStudentById(@PathVariable int id) {
        return Response.success(studentService.getStudentById(id));
    }

    @GetMapping("")
    public Response<List<StudentDTO>> getStudents() {
        return Response.success(studentService.getStudents());
    }

    @PostMapping("")
    public Response<Long> addNewStudent(@RequestBody StudentDTO studentDTO) {
        return Response.success(studentService.addNewStudent(studentDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("/{id}")
    public Response<StudentDTO> updateStudentById(@PathVariable int id,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String email) {
       return Response.success(studentService.updateStudentById(id, name, email));
    }
}
