package com.challenge.student.ms_student.controller;

import com.challenge.student.ms_student.entity.Student;
import com.challenge.student.ms_student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<String>> createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student)
                .map(savedStudent -> ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
    }

    @GetMapping("/active")
    public Flux<Student> getAllActiveStudents() {
        return studentService.getAllActiveStudents();
    }
}
