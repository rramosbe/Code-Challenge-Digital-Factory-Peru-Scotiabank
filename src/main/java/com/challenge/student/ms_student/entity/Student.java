package com.challenge.student.ms_student.entity;

import com.challenge.student.ms_student.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private Status status;
    private int age;
}
