package com.challenge.student.ms_student.service;

import com.challenge.student.ms_student.entity.Student;
import com.challenge.student.ms_student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Mono<Student> saveStudent(Student student) {
        if (student.getId() <= 0) {
            return Mono.error(new IllegalArgumentException("El ID debe ser mayor que 0"));
        }
        if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
            return Mono.error(new IllegalArgumentException("El nombre no puede ser nulo o vacío"));
        }
        if (student.getLastName() == null || student.getLastName().isEmpty()) {
            return Mono.error(new IllegalArgumentException("El apellido no puede ser nulo o vacío"));
        }
        if (student.getAge() <= 0) {
            return Mono.error(new IllegalArgumentException("La edad debe ser mayor que 0"));
        }
        if (student.getStatus() == null) {
            return Mono.error(new IllegalArgumentException("El estado debe ser 'ACTIVO' o 'INACTIVO'"));
        }
        return studentRepository.save(student);
    }

    public Flux<Student> getAllActiveStudents() {
        return studentRepository.findAllByActiveStatus();
    }
}
