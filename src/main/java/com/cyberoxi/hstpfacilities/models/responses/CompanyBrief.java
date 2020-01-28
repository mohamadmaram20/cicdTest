package com.cyberoxi.hstpfacilities.models.responses;

import com.cyberoxi.hstpfacilities.models.*;
import com.cyberoxi.hstpfacilities.models.audits.AuditModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyBrief {

    private long id;
    private String name;
    private String registrationNumber;
    private String nationalId;
}
