package com.platformcommons.service;

import com.platformcommons.exceptions.CourseException;
import com.platformcommons.exceptions.LoginException;
import com.platformcommons.exceptions.StudentException;
import com.platformcommons.model.Admin;
import com.platformcommons.model.Course;
import com.platformcommons.model.CurrentUserSession;
import com.platformcommons.model.Student;
import com.platformcommons.repository.AdminRepo;
import com.platformcommons.repository.CourseRepo;
import com.platformcommons.repository.CurrentSessionRepo;
import com.platformcommons.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private CurrentSessionRepo currentSessionRepo;



    @Override
    public Admin createAdminAccount( Admin admin) throws LoginException {

        List<Admin> admins = adminRepo.findAdminByMobile(admin.getMobileNumber());

        if(admins.isEmpty()) {

            return adminRepo.save(admin);
        }
        throw new LoginException("Duplicate Mobile Number [ Already Registered with different customer ] ");

    }

    /*----------------------------------------  Add Student Implementation -------------------------------------------*/
    @Override
    public Student admitStudent(String key, Student student) throws StudentException, LoginException {

        // Validation Current User
        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        //Find Student
        Student students = studentRepo.findBystudentCode(student.getStudentCode());
        if (students != null){
            throw new StudentException("Student Already exist with given Student Code ");
        }

        Student admitstudent = studentRepo.save(student);

        return admitstudent;
    }


    /*----------------------------------------  Add Course Implementation  -------------------------------------------*/
    @Override
    public Course createCourse(String key, Course course) throws CourseException, LoginException {

        // Validation Current User
        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        //Find Course
        Course courses = courseRepo.findBycourseName(course.getCourseName());
        if (courses != null){
            new CourseException("Course With Given Name Already Exist");
        }

        Course createCourse = courseRepo.save(course);

        return createCourse;
    }


    /*----------------------------------------  Get Student By Name IMPL -------------------------------------------*/
    @Override
    public Student getStudentByName(String key, String student_name) throws StudentException, LoginException {

        // Validation Current User
        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        //Find Student
        Student student = studentRepo.findByStudentName(student_name);

        if (student == null){
            throw new StudentException("No student found with the Student Name : " + student_name);
        }
        return student;
    }


    /*----------------------------------------  Get Student By Course IMPL -------------------------------------------*/
    @Override
    public List<Student> getStudentsByCourse(String key, String courseName) throws StudentException, CourseException, LoginException {

        // Validation Current User
        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        //Find Course
        Course course = courseRepo.findBycourseName(courseName);

        if (course == null){
            throw new CourseException("No Course Found with given courseName : " + courseName);
        }

        List<Student> students = course.getStudents();

        if (students.isEmpty()){
            throw new StudentException("No Student Allocated to the course");
        }

        return students;
    }


    /*----------------------------------------  Add Student To Course IMPL -------------------------------------------*/
    @Override
    public String addStudentToTheCourse(String key, String courseName, Integer roll_number) throws StudentException, CourseException, LoginException {

        // Validation Current User
        CurrentUserSession currUserSession = currentSessionRepo.findByUuid(key);
        if(currUserSession==null) {
            throw new LoginException("Admin Does Not LoggedIn");
        }

        Course course = courseRepo.findBycourseName(courseName);
        Student student = studentRepo.findBystudentCode(roll_number);

        if (student != null) {
            if (!student.equals(course)) {
                student.getCourses().add(course);  // allocate Course
            }
            studentRepo.save(student);

            return "Student with Roll Number ' "+ roll_number + " ' and Name ' "+ student.getStudentName() + " ' allocated to ' " + course.getCourseName() + " ' Course Successfully." + "[Course Id : "+ course.getCourseId() + " ]";
        }

        return "Unable to Allocate Given Course To Student";
    }



}
