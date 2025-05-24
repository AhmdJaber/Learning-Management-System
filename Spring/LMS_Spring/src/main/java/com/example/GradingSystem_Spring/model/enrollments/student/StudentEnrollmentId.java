package com.example.GradingSystem_Spring.model.enrollments.student;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentEnrollmentId {
    private Long studentID;
    private String courseID;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StudentEnrollmentId that = (StudentEnrollmentId) object;
        return Objects.equals(studentID, that.studentID) && Objects.equals(courseID, that.courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, courseID);
    }
}
