package com.example.GradingSystem_Spring.model.enrollments.teacher;

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
public class TeacherEnrollment {
    @EmbeddedId
    private TeacherEnrollmentId id;
}
