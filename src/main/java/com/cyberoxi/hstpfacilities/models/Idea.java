package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Mohamad Zarei Maram
 * @author Mohamad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/2020
 */
@Entity
@Table(name = "ideas")
@Data
public class Idea extends AuditModel {

    private String title;
    private byte field;
    private String usage;
    private String summary;
}
