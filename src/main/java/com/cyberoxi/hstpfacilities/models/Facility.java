package com.cyberoxi.hstpfacilities.models;

import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Mohamad Zarei Maram
 * @author Mohamad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/2020
 */
@Entity
@Table(name = "facilities")
@Data
public class Facility extends AuditModel {

    private String contractNumber;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "2020-02-02")
    private Date contractDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "2020-02-02")
    private Date lastApprovalDateCreditCommittee; //تاریخ آخرین مصوبه کارگروه اعتبارات

    private String approvedText;
    private byte changeType;
    private long approvedAmount; //مبلغ مصوب
    private long totalReceivedFacility; //کل تسهیلات دریافتی(میلیون ریال)
    private int repaymentMonthsNumber; //تعداد ماههای باز پرداخت
    private long monthlyRepaymentAmount; //مبلغ اقساط ماهانه باز پرداخت تسهيلات

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "2020-02-02")
    private Date installmentsStartDate; //تاريخ شروع اقساط

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "2020-02-02")
    private Date installmentsEndDate; //تاريخ خاتمه اقساط

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "2020-02-02")
    private Date installmentsRepaymentDate; //تاریخ آخرین بازپرداخت اقساط

    private long installmentsRepaymentAmount; //مبلغ بازپرداخت اقساط

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(example = "2020-02-02")
    private Date recentChangeApprovedDate; //تاریخ آخرین تغییرات مصوبه
}
