package io.icejoywoo.springboot.controller;

import io.icejoywoo.springboot.Response;
import io.icejoywoo.springboot.dto.StudentDTO;
import io.icejoywoo.springboot.service.StudentService;
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
