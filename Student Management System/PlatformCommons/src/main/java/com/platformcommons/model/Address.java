package com.platformcommons.model;

import com.platformcommons.model.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence")
    private Integer addressId;
    private String area;
    private String district;
    private String state;
    private String pincode;
    @Enumerated
    private AddressType addressType;


    @ManyToOne
    @JoinColumn(name = "student_code")
    private Student student;

}
