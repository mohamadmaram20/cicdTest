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
public class CompanyReport {

    private int installmentsNumber; //تعداد اقساط وام
    private int loanAmountPerMonth; //مبلغ وام در هر ماه
    private int debtInstallmentsArrears; //مبلغ بدهی اقساط وام معوقه
    private List<DateNumber> debtEstablishmentEachYear; //مبلغ بدهی استقرار به تفکیک سال
    private List<DateNumber> debtEstablishmentEachMonth; //مبلغ بدهی استقرار به تفکیک ماه
    private List<DateNumber> facilityPaidEachMonth; //مبلغ پرداختی تسهیلات به تفکیک هر ماه
    private List<DateNumber> establishmentPaidEachMonth; //مبلغ پرداختی استقرار به تفکیک هر ماه
    private int totalFacilityAmountPaid; //کل مبلغ پرداختی تسهیلات
    private int totalEstablishmentAmountPaid; //کل مبلغ پرداختی استقرار
    private int debtFacilityRemained; //مانده بدهی تسهیلات
    private int debtEstablishmentRemained; //مانده بدهی استقرار
}
