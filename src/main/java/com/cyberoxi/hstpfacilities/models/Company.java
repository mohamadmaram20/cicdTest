package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mohamad Zarei Maram
 * @author Mohamad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/2020
 */
@Entity
@Table(name = "companies")
@Data
public class Company extends AuditModel {

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    private byte type;
    private byte branch;
    private byte receptionType;

    private String registrationNumber;
    private String nationalId;

    private String CeoFirstName;
    private String CeoLastName;
    private String CeoNationalCode;
    private String CeoPhoneNumber;

    private String phoneNumber;
    private String website;
    private String fax;
    private String postalAddress;
    private String email;
    private String avatar;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> signatureOwners = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> teammates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Establishment> establishments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Facility> facilities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceptionDate> receptionDates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Idea> idea = new ArrayList<>();
}
