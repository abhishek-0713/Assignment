package com.platformcommons.model;

import com.platformcommons.model.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDTO {

    private Integer roll_number;
    private Integer studentCode;
    private String studentName;
    private String email;
    private String mobile;
    private String parent_name;
    private LocalDate date_of_birth;
    private Set<String> courses = new HashSet<>();

}
