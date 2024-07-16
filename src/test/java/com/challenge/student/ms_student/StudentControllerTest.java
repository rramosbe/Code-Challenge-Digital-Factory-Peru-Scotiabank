package com.challenge.student.ms_student;

import com.challenge.student.ms_student.controller.StudentController;
import com.challenge.student.ms_student.entity.Student;
import com.challenge.student.ms_student.enums.Status;
import com.challenge.student.ms_student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = StudentController.class)
class StudentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setStatus(Status.ACTIVE);
        student.setAge(20);
    }

    @Test
    void createStudent() {
        Mockito.when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(Mono.just(student));

        webTestClient.post()
                .uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(student), Student.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class).isEqualTo("Student created successfully");
    }

    @Test
    void getAllActiveStudents() {
        Mockito.when(studentService.getAllActiveStudents()).thenReturn(Flux.just(student));

        webTestClient.get()
                .uri("/students/active")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Student.class).contains(student);
    }
}
