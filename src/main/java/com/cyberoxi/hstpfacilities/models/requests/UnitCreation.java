package com.cyberoxi.hstpfacilities.models.requests;

import com.cyberoxi.hstpfacilities.models.Credential;
import com.cyberoxi.hstpfacilities.models.Person;
import com.cyberoxi.hstpfacilities.models.ReceptionDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 3/3/2020
 */
@Data
public class UnitCreation {
    /*@NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String password;
    private String role;*/

    private Credential credential;

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

    //@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    private String phoneNumber;
    private String website;
    private String fax;
    private String postalAddress;
    private String email;
    private String avatar;

    private List<Person> signatureOwners;
    private List<Person> teammates;
    private List<ReceptionDate> receptionDates;
}
