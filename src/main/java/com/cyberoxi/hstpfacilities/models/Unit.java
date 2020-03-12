package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
@Table(name = "units")
@Data
public class Unit extends AuditModel {

    /*@NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String password;*/

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "2020-02-02")
    private Date registrationDate;

    private String name;
    private byte type;
    private byte branch;
    private byte receptionType;

    private String registrationNumber;
    private String nationalId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    private String phoneNumber;
    private String website;
    private String fax;
    private String postalAddress;
    private String email;
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Credential credential = new Credential();

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
    private List<Idea> ideas = new ArrayList<>();
}
