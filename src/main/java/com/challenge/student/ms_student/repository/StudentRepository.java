package com.challenge.student.ms_student.repository;

import com.challenge.student.ms_student.entity.Student;
import com.challenge.student.ms_student.enums.Status;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepository {
    private final Map<Integer, Student> database = new ConcurrentHashMap<>();

    public Mono<Student> save(Student student) {
        if (database.containsKey(student.getId())) {
            return Mono.error(new IllegalArgumentException("El estudiante con este ID ya fue registrado"));
        }
        database.put(student.getId(), student);
        return Mono.just(student);
    }

    public Flux<Student> findAllByActiveStatus() {
        Collection<Student> students = database.values();
        return Flux.fromStream(students.stream().filter(student -> Status.ACTIVE.equals(student.getStatus())));
    }
}
