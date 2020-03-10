package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
public class User extends AuditModel {

    private String firstName;
    private String lastName;
    private String email;
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Credential credential = new Credential();
}
