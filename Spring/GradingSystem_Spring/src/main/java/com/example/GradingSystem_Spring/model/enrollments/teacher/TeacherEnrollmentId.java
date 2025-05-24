package com.example.GradingSystem_Spring.model.enrollments.teacher;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherEnrollmentId {
    private Long teacherID;
    private String courseID;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TeacherEnrollmentId that = (TeacherEnrollmentId) object;
        return Objects.equals(teacherID, that.teacherID) && Objects.equals(courseID, that.courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherID, courseID);
    }
}
