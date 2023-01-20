package com.platformcommons.service;

import com.platformcommons.exceptions.CourseException;
import com.platformcommons.exceptions.LoginException;
import com.platformcommons.exceptions.StudentException;
import com.platformcommons.model.Admin;
import com.platformcommons.model.Course;
import com.platformcommons.model.Student;

import java.util.List;

public interface AdminService {

    /*-------------------------------- Add Admin  ---------------------------------*/
    public Admin createAdminAccount(Admin admin) throws LoginException;


    /*-------------------------------- Add Student  ---------------------------------*/
    public Student admitStudent(String key, Student student) throws StudentException, LoginException;


    /*-------------------------------- Add Course  ---------------------------------*/
    public Course createCourse(String key, Course course) throws CourseException, LoginException;


    /*-------------------------------- Get Student By Name  ---------------------------------*/
    public Student getStudentByName(String key,String student_name) throws StudentException, LoginException;


    /*-------------------------------- Get Student By Course  ---------------------------------*/
    public List<Student> getStudentsByCourse(String key,String courseName ) throws StudentException, CourseException, LoginException;


    /*-------------------------------- Add Student To Course  ---------------------------------*/
    public String addStudentToTheCourse(String key, String courseName, Integer roll_number) throws StudentException, CourseException, LoginException;


}
