package com.platformcommons.controller;

import com.platformcommons.exceptions.CourseException;
import com.platformcommons.exceptions.StudentException;
import com.platformcommons.model.Address;
import com.platformcommons.model.Course;
import com.platformcommons.model.Student;
import com.platformcommons.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    /*--------------------------------------------  [Put] Update Student  --------------------------------------------------*/
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent( @RequestParam Integer student_code, @RequestParam String date_of_birth, @RequestParam String email, @RequestParam String mobile, @RequestParam String parentsName, @RequestBody List<Address> address) throws StudentException {

        LocalDate date = LocalDate.parse(date_of_birth);

        Student updateStudent = studentService.updateStudent(student_code, date, email, mobile, parentsName, address);
        return new ResponseEntity<Student>(updateStudent, HttpStatus.ACCEPTED);

    }


    /*--------------------------------------------  [Get] Courses  --------------------------------------------------*/
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam Integer student_code, @RequestParam String date_of_birth) throws CourseException, StudentException {

        LocalDate date = LocalDate.parse(date_of_birth);

        List<Course> courses =  studentService.searchCourses(student_code, date);
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);

    }


    /*--------------------------------------------  [Delete] Course  --------------------------------------------------*/
    @DeleteMapping("/course")
    public ResponseEntity<String> leaveCourse(@RequestParam Integer student_code, @RequestParam String date_of_birth, @RequestParam Integer courseId) throws CourseException, StudentException {

        LocalDate date = LocalDate.parse(date_of_birth);

        String deleteCourse = studentService.leaveCourse(student_code, date, courseId);
        return new ResponseEntity<String>(deleteCourse, HttpStatus.OK);

    }


}
