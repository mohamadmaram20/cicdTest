package com.cyberoxi.hstpfacilities.models;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Mohamad Mahdi Kahool
 * @version 0.0.1
 * @since 1/28/2020
 */
@Entity
@Table(name = "persons")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String nationalCode;
    private String phoneNumber;
}
