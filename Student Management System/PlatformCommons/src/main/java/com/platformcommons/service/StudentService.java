package com.platformcommons.service;

import com.platformcommons.exceptions.CourseException;
import com.platformcommons.exceptions.StudentException;
import com.platformcommons.model.Address;
import com.platformcommons.model.Course;
import com.platformcommons.model.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentService{


    /*--------------------------------------------  Update Student  --------------------------------------------------*/
    public Student updateStudent(Integer student_code, LocalDate date_of_birth, String email, String mobile, String parentsName, List<Address> address) throws StudentException;


    /*--------------------------------------------  Search Courses by Student  --------------------------------------------------*/
    public List<Course> searchCourses(Integer student_code, LocalDate date_of_birth) throws CourseException, StudentException;


    /*--------------------------------------------  Delete Course  --------------------------------------------------*/
    public String leaveCourse(Integer student_code, LocalDate date_of_birth, Integer courseId) throws CourseException, StudentException;


}
