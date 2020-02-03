package com.cyberoxi.hstpfacilities.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Data
@AllArgsConstructor
public class AdminReport {

    private long unitId;
    private String unitName;
    private byte branch;
    private int debtFacilityRemained; //مانده بدهی تسهیلات
    private int debtEstablishmentRemained; //مانده بدهی استقرار

}
