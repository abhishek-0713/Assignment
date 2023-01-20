package com.platformcommons.repository;

import com.platformcommons.exceptions.CourseException;
import com.platformcommons.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {

    public Course findBycourseName(String courseName) throws CourseException;
}
