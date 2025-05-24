package com.example.GradingSystem_Spring.service;

import com.example.GradingSystem_Spring.model.entities.Course;
import com.example.GradingSystem_Spring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElseThrow( () ->
                new NoSuchElementException("Course with id " + id + " not found")
        );
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(String id, Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setId(updatedCourse.getId());
            course.setName(updatedCourse.getName());
            return courseRepository.save(course);
        }).orElseThrow(() -> new NoSuchElementException("Course with ID " + id + " not found"));
    }

    public boolean deleteCourse(String id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
