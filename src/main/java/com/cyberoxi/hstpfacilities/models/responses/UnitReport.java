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
public class UnitReport {

    private long installmentsNumber; //تعداد اقساط وام
    private long loanAmountPerMonth; //مبلغ وام در هر ماه
    private long debtInstallmentsArrears; //مبلغ بدهی اقساط وام معوقه
    private List<DateNumber> debtEstablishmentEachYear; //مبلغ بدهی استقرار به تفکیک سال
    private List<DateNumber> debtEstablishmentEachMonth; //مبلغ بدهی استقرار به تفکیک ماه
    private List<DateNumber> facilityPaidEachMonth; //مبلغ پرداختی تسهیلات به تفکیک هر ماه
    private List<DateNumber> establishmentPaidEachMonth; //مبلغ پرداختی استقرار به تفکیک هر ماه
    private long totalFacilityAmountPaid; //کل مبلغ پرداختی تسهیلات
    private long totalEstablishmentAmountPaid; //کل مبلغ پرداختی استقرار
    private long debtFacilityRemained; //مانده بدهی تسهیلات
    private long debtEstablishmentRemained; //مانده بدهی استقرار
}
