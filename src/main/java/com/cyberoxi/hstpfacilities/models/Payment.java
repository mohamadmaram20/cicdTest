package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.Persistent;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

import static com.cyberoxi.hstpfacilities.GlobalVariables.TRACKING_CODE_LENGTH;

/**
 * @author Mohamad Zarei Maram
 * @author Mohamad Mahdi Kahool
 * @version 0.0.1
 * @since 2/2/2020
 */
@Entity
@Table(name = "payments")
@Data
public class Payment extends AuditModel {

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(example = "2020-02-02 00:00")
    private Date transactionDate;

    @Column(nullable = false, updatable = false)
    private long amount;

    //TODO chang fields
    private byte type; // 1 = pos, 2 = cardToCard, 3 = cash, 4 = internet
    private String referenceId;

    private long contractId;

    @ApiModelProperty(example = "e")
    private char contractType; // e = establishment, f = facility
    private long unitId;

    @Column(length = TRACKING_CODE_LENGTH)
    @ApiModelProperty(hidden = true)
    private String trackingCode;

    @Persistent
    @Formula("(SELECT units.name FROM units where units.id = unit_id)")
    private String unitName;
}
