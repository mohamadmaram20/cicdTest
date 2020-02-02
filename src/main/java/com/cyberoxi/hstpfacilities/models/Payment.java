package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
    private Date transactionDate;

    @Column(nullable = false, updatable = false)
    private int amount;

    private byte type; // 1 = pos, 2 = cardToCard, 3 = cash, 4 = internet
    private long referenceId;

    private long contractId;
    private char contractType; // e = establishment, f = facility
    private long unitId;

    @Column(length = 8)
    private String trackingCode;
}
