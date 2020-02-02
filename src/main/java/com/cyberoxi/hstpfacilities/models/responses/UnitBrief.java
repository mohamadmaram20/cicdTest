package com.cyberoxi.hstpfacilities.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mohamad Zarei Maram
 * @author Mohamad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/2020
 */

@Data
@AllArgsConstructor
public class UnitBrief {

    private long id;
    private String name;
    private String registrationNumber;
    private String nationalId;
}
