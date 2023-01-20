package com.platformcommons.service;

import com.platformcommons.exceptions.CourseException;
import com.platformcommons.exceptions.StudentException;
import com.platformcommons.model.Address;
import com.platformcommons.model.Course;
import com.platformcommons.model.Student;
import com.platformcommons.repository.CourseRepo;
import com.platformcommons.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;


    /*--------------------------------------------  Update Course IMPL  --------------------------------------------------*/
    @Override
    public Student updateStudent(Integer student_code, LocalDate date_of_birth, String email, String mobile, String parentsName, List<Address> address) throws StudentException {

        // student validation - [student-code]
        Student updateStudent = studentRepo.findBystudentCode(student_code);
        if (updateStudent == null){
            throw new StudentException("No student found with the Student Code : " + student_code);
        }

        // student validation - [date_of_birth]
        updateStudent = studentRepo.findByDateOfBirth(date_of_birth);
        if (updateStudent == null){
            throw new StudentException("No student found with the Given Data Of Birth : " + date_of_birth);
        }

        updateStudent.setEmail(email);
        updateStudent.setMobile(mobile);
        updateStudent.setParent_name(parentsName);
        updateStudent.setAddresses(address);

        return studentRepo.save(updateStudent);
    }


    /*--------------------------------------------  Search Course IMPL --------------------------------------------------*/
    @Override
    public List<Course> searchCourses(Integer student_code, LocalDate date_of_birth) throws CourseException, StudentException {

        // student validation - [student-code]
        Student student = studentRepo.findBystudentCode(student_code);
        if (student == null){
            throw new StudentException("No student found with the Student Code : " + student_code);
        }

        // student validation - [date_of_birth]
        student = studentRepo.findByDateOfBirth(date_of_birth);
        if (student == null){
            throw new StudentException("No student found with the Given Data Of Birth : " + date_of_birth);
        }


        List<Course> searchCourse = student.getCourses();
        if (searchCourse.isEmpty()){
            throw new CourseException("No Courses Allocated to the Student yet.");
        }

        return searchCourse;
    }


    /*--------------------------------------------  Delete Course  IMPL --------------------------------------------------*/
    @Override
    public String leaveCourse(Integer student_code, LocalDate date_of_birth, Integer courseId) throws CourseException, StudentException {

        // student validation - [student-code]
        Student student = studentRepo.findBystudentCode(student_code);
        if (student == null){
            throw new StudentException("No student found with the Student Code : " + student_code);
        }

        // student validation - [date_of_birth]
        student = studentRepo.findByDateOfBirth(date_of_birth);
        if (student == null){
            throw new StudentException("No student found with the Given Data Of Birth : " + date_of_birth);
        }


        List<Course> course = student.getCourses();
        if (course.isEmpty()){
            throw new CourseException("No Courses Allocated to the Student yet.");
        }

        Optional<Course> course1 = courseRepo.findById(courseId);

        courseRepo.deleteById(courseId);
        studentRepo.deleteAllByIdInBatch(Collections.singleton(course1.get().getCourseId()));

        student.getCourses().clear();

        return "Course Deleted Successfully : " ;

    }


}
