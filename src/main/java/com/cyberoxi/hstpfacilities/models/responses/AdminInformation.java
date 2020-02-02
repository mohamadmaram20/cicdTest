package com.cyberoxi.hstpfacilities.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Data
@AllArgsConstructor
public class AdminInformation {

    private long units;
    private long facilities;
    private long establishments;

    private long totalArrears;
    private long establishmentArrears;
    private long facilitiesArrears;

    private List<AdminReport> adminReports;
}
