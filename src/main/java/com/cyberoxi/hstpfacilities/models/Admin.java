package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Entity
@Table(name = "admins")
@Data
public class Admin extends AuditModel {

    private String firstName;
    private String lastName;
    private String email;
    private String username;

    //@JsonIgnore
    private String password;

    private String avatar;

    private int accessLevel;
}
