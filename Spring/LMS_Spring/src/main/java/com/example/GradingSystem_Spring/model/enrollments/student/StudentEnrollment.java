package com.example.GradingSystem_Spring.model.enrollments.student;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentEnrollment {
    @EmbeddedId
    private StudentEnrollmentId id;
    private double grade;

    public StudentEnrollment(StudentEnrollmentId id) {
        this.id = id;
    }
}
