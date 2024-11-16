package io.icejoywoo.springboot.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testFindByEmail() {
        String email = "test@test.com";
        Student student = new Student();
        // Cannot set id for student, which will cause error:
        // jakarta.persistence.EntityExistsException: detached entity passed to persist
        student.setName("test");
        student.setEmail(email);
        student.setAge(20);

        Student ret = testEntityManager.persist(student);
        int id = ret.getId();
        System.out.println("id : " + id);

        List<Student> students = studentRepository.findByEmail(email);
        assertThat(students).hasSize(1);
        assertThat(students).contains(student);
    }
}
