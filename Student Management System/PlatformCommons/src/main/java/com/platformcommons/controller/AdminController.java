package com.platformcommons.controller;

import com.platformcommons.exceptions.CourseException;
import com.platformcommons.exceptions.LoginException;
import com.platformcommons.exceptions.StudentException;
import com.platformcommons.model.Admin;
import com.platformcommons.model.Course;
import com.platformcommons.model.Student;
import com.platformcommons.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /*--------------------------------------------   Admin Account  ------------------------------------------------*/
    @PostMapping("/add")
    public ResponseEntity<Admin> createAccount(@Valid @RequestBody Admin admin) throws LoginException, ValidationException{

        if (admin == null){
            throw new LoginException("Invalid Details");
        }
        return new ResponseEntity<Admin>(adminService.createAdminAccount(admin), HttpStatus.CREATED);
    }


    /*---------------------------------------------  Post Student  ---------------------------------------------*/
    @PostMapping("/student")
    public ResponseEntity<Student> admitStudent(@RequestParam String key, @Valid @RequestBody Student student ) throws StudentException, LoginException, ValidationException {

        if (student == null){
            throw new StudentException(" Invalid Student details ");
        }
        Student admitStudent = adminService.admitStudent( key, student);
        return new ResponseEntity<Student>(admitStudent, HttpStatus.ACCEPTED);

    }


    /*---------------------------------------------  Post Course  ---------------------------------------------*/
    @PostMapping("/course")
    public ResponseEntity<Course> createCourse(@RequestParam String key, @Valid @RequestBody Course course) throws StudentException, CourseException, LoginException, ValidationException {

        Course createCourse = adminService.createCourse(key, course);
        return new ResponseEntity<Course>(createCourse, HttpStatus.ACCEPTED);

    }


    /*---------------------------------------------  Get Students  ---------------------------------------------*/
    @GetMapping("/student/name")
    public ResponseEntity<Student> getStudentByName(@RequestParam String key, @RequestParam String student_name) throws CourseException, StudentException, LoginException {

        Student student = adminService.getStudentByName(key, student_name);
        return new ResponseEntity<Student>(student, HttpStatus.OK);

    }


    /*---------------------------------------------  Get Students By Course  ---------------------------------------------*/
    @GetMapping("/students/course")
    public ResponseEntity<List<Student>> getStudentsByCourse( @RequestParam String key,  @RequestParam String courseName ) throws CourseException, StudentException, LoginException {

        List<Student> students =  adminService.getStudentsByCourse(key,courseName);
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }


    /*---------------------------------------------  Get Allocated Student To Course ---------------------------------------------*/
    @GetMapping("/allocate")
    public ResponseEntity<String> addStudentToTheCourse(@RequestParam String key, @RequestParam String courseName, @RequestParam Integer student_code) throws StudentException, CourseException, LoginException {

        return new ResponseEntity<String>(adminService.addStudentToTheCourse(key, courseName,student_code), HttpStatus.OK);

    }


}
