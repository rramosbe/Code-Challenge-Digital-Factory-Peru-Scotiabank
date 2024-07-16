package com.challenge.student.ms_student;

import com.challenge.student.ms_student.entity.Student;
import com.challenge.student.ms_student.enums.Status;
import com.challenge.student.ms_student.repository.StudentRepository;
import com.challenge.student.ms_student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @Test
    void saveStudentSuccess() {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setStatus(Status.ACTIVE);
        student.setAge(20);

        when(studentRepository.save(any(Student.class))).thenReturn(Mono.just(student));

        StepVerifier.create(studentService.saveStudent(student))
                .expectNext(student)
                .verifyComplete();
    }

    @Test
    void getAllActiveStudents() {
        Student student1 = new Student();
        student1.setId(1);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setStatus(Status.ACTIVE);
        student1.setAge(20);

        Student student2 = new Student();
        student2.setId(2);
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setStatus(Status.ACTIVE);
        student2.setAge(22);

        when(studentRepository.findAllByActiveStatus()).thenReturn(Flux.just(student1, student2));

        StepVerifier.create(studentService.getAllActiveStudents())
                .expectNext(student1, student2)
                .verifyComplete();
    }
}

