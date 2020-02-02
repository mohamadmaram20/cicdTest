package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.models.responses.UnitBrief;
import com.cyberoxi.hstpfacilities.models.responses.UnitReport;
import com.cyberoxi.hstpfacilities.models.responses.DateNumber;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohamad Zarei Maram
 * @author Mohamad Mahdi Kahool
 * @version 0.0.1
 * @since 1/22/2020
 */
@Service
public class UnitsServiceImpl implements UnitsService {

    private UnitsRepository unitsRepository;

    @Autowired
    public UnitsServiceImpl(UnitsRepository unitsRepository) {
        this.unitsRepository = unitsRepository;
    }

    @Override
    public Iterable<Unit> getUnits() {
        return unitsRepository.findAll();
    }

    @Override
    public Iterable<UnitBrief> getUnitsBrief() {
        List<UnitBrief> unitsBrief = new ArrayList<>();
        unitsRepository.findAll().forEach(unit -> {
            unitsBrief.add(new UnitBrief(unit.getId(), unit.getName(), unit.getRegistrationNumber(), unit.getNationalId()));
        });
        return unitsBrief;
    }

    @Override
    public Unit getUnit(long id) {
        return unitsRepository.findById(id).get();
    }

    @Override
    public Unit saveUnit(Unit unit) {
        return unitsRepository.save(unit);
    }

    @Override
    public Unit updateUnit(long id, Unit unit) {
        Unit findUnit = unitsRepository.findById(id).get();
        // TODO update fields
        return unitsRepository.save(findUnit);
    }

    @Override
    public UnitReport getUnitReport(long id) {
        Unit findUnit = unitsRepository.findById(id).get();
        // TODO fill fields
        int installmentsNumber = 0; //تعداد اقساط وام
        int loanAmountPerMonth = 0; //مبلغ وام در هر ماه
        int debtInstallmentsArrears = 0; //مبلغ بدهی اقساط وام معوقه
        List<DateNumber> debtEstablishmentEachYear = new ArrayList<>(); //مبلغ بدهی استقرار به تفکیک سال
        List<DateNumber> debtEstablishmentEachMonth = new ArrayList<>(); //مبلغ بدهی استقرار به تفکیک ماه
        List<DateNumber> facilityPaidEachMonth = new ArrayList<>(); //مبلغ پرداختی تسهیلات به تفکیک هر ماه
        List<DateNumber> establishmentPaidEachMonth = new ArrayList<>(); //مبلغ پرداختی استقرار به تفکیک هر ماه
        int totalFacilityAmountPaid = 0; //کل مبلغ پرداختی تسهیلات
        int totalEstablishmentAmountPaid = 0; //کل مبلغ پرداختی استقرار
        int debtFacilityRemained = 0; //مانده بدهی تسهیلات
        int debtEstablishmentRemained = 0; //مانده بدهی استقرار

        for (Facility facility : findUnit.getFacilities()) {
            installmentsNumber = installmentsNumber + facility.getRepaymentMonthsNumber();
            loanAmountPerMonth = loanAmountPerMonth + facility.getInstallmentsRepaymentAmount();
            debtInstallmentsArrears = debtInstallmentsArrears + facility.getInstallmentsRepaymentAmount();

            totalFacilityAmountPaid = totalFacilityAmountPaid + facility.getApprovedAmount();
            debtFacilityRemained = debtFacilityRemained + facility.getApprovedAmount();
        }
        for (Establishment establishment : findUnit.getEstablishments()) {
            totalEstablishmentAmountPaid = totalEstablishmentAmountPaid + establishment.getFinalContractMonthlyAmount();
            debtEstablishmentRemained = debtEstablishmentRemained + establishment.getFinalContractMonthlyAmount();
        }

        debtEstablishmentEachYear.add(new DateNumber("1396", 0));
        debtEstablishmentEachYear.add(new DateNumber("1397", 0));
        debtEstablishmentEachYear.add(new DateNumber("1398", 0));

        debtEstablishmentEachMonth.add(new DateNumber("فروردین", 0));
        debtEstablishmentEachMonth.add(new DateNumber("اردیبهشت", 0));
        debtEstablishmentEachMonth.add(new DateNumber("خرداد", 0));
        debtEstablishmentEachMonth.add(new DateNumber("تیر", 0));
        debtEstablishmentEachMonth.add(new DateNumber("مرداد", 0));
        debtEstablishmentEachMonth.add(new DateNumber("شهریور", 0));
        debtEstablishmentEachMonth.add(new DateNumber("مهر", 0));
        debtEstablishmentEachMonth.add(new DateNumber("آبان", 0));
        debtEstablishmentEachMonth.add(new DateNumber("آذر", 0));
        debtEstablishmentEachMonth.add(new DateNumber("دی", 0));
        //debtEstablishmentEachMonth.add(new DateNumber("بهمن", 0));
        //debtEstablishmentEachMonth.add(new DateNumber("اسفند", 0));
        return new UnitReport(
                installmentsNumber,
                loanAmountPerMonth,
                debtInstallmentsArrears,
                debtEstablishmentEachYear,
                debtEstablishmentEachMonth,
                /*facilityPaidEachMonth*/debtEstablishmentEachMonth,
                /*establishmentPaidEachMonth*/debtEstablishmentEachMonth,
                totalFacilityAmountPaid,
                totalEstablishmentAmountPaid,
                debtFacilityRemained,
                debtEstablishmentRemained);
    }
}
