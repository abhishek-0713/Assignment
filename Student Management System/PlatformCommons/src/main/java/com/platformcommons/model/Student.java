package com.platformcommons.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platformcommons.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence")
    private Integer roll_number;

    @NotNull
    @Column(unique = true, name = "student_code")
    private Integer studentCode;

    @NotNull
    @Size(min = 3, message = "Invalid Student name [ contains at least 3 characters ]")
    private String studentName;

    @NotNull
    @Email(message = "Invalid Email Format [ abc@gmail.com ]")
    private String email;

    @NotNull
    @Size(min = 10, max = 10 ,message = "Invalid Mobile Number [ 10 Digit Only ] ")
    private String mobile;

    @NotNull
    @Size(min = 3, message = "Invalid Parent name [ contains at least 3 characters ]")
    private String parent_name;

    @NotNull
    @Column(unique = true)
    private LocalDate dateOfBirth;

    @Enumerated
    private Gender gender;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "STUDENTS_COURSES", joinColumns = { @JoinColumn(name = "roll_number") }, inverseJoinColumns = { @JoinColumn(name = "courseId") })
    private List<Course> courses;

    public Student(Integer studentCode, String studentName, String email, String mobile, String parent_name, LocalDate dateOfBirth, Gender gender) {
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.email = email;
        this.mobile = mobile;
        this.parent_name = parent_name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Student(Integer studentCode, String studentName, String email, String mobile, String parent_name, LocalDate dateOfBirth, Gender gender, List<Address> addresses) {
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.email = email;
        this.mobile = mobile;
        this.parent_name = parent_name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.addresses = addresses;
    }


}
