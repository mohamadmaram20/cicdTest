package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 3/9/2020
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "credentials")
@Data
public class Credential extends AuditModel {

    @NotBlank
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String password;
    private String role;
}
