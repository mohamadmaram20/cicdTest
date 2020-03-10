package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Credential;
import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.DateNumber;
import com.cyberoxi.hstpfacilities.models.responses.UnitBrief;
import com.cyberoxi.hstpfacilities.models.responses.UnitReport;
import com.cyberoxi.hstpfacilities.repositories.CredentialRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UnitServiceImpl implements UnitService {

    private UnitRepository unitRepository;
    private AuditorService auditorService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private CredentialRepository credentialRepository;

    @Autowired
    public UnitServiceImpl(UnitRepository unitRepository, AuditorService auditorService, BCryptPasswordEncoder bCryptPasswordEncoder, CredentialRepository credentialRepository) {
        this.unitRepository = unitRepository;
        this.auditorService = auditorService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.credentialRepository = credentialRepository;
    }

    @Override
    public Iterable<Unit> getUnits() {
        return unitRepository.findAll();
    }

    @Override
    public Iterable<UnitBrief> getUnitsBrief() {
        List<UnitBrief> unitsBrief = new ArrayList<>();
        unitRepository.findAll().forEach(unit -> {
            unitsBrief.add(new UnitBrief(unit.getId(), unit.getName(), unit.getRegistrationNumber(), unit.getNationalId()));
        });
        return unitsBrief;
    }

    @Override
    public Unit getUnit(long id) {
        return unitRepository.findById(id).get();
    }

    @Override
    public Unit saveUnit(Unit unit) {
        Credential credential = unit.getCredential();
        credential.setPassword(bCryptPasswordEncoder.encode(credential.getPassword()));
        Credential savedCredential = credentialRepository.save(credential);
        unit.setCredential(savedCredential);
        return unitRepository.save(unit);
    }

    @Override
    public Unit updateUnit(long id, Unit unit) {
        Unit findUnit = unitRepository.findById(id).get();
        // TODO update fields
        return unitRepository.save(findUnit);
    }

    @Override
    public UnitReport getUnitReport(long id) {
        Unit unit = unitRepository.findById(id).get();
        // TODO fill fields
        long installmentsNumber = 0; //تعداد اقساط وام
        long loanAmountPerMonth = 0; //مبلغ وام در هر ماه
        long debtInstallmentsArrears = 0; //مبلغ بدهی اقساط وام معوقه
        List<DateNumber> debtEstablishmentEachYear = new ArrayList<>(); //مبلغ بدهی استقرار به تفکیک سال
        List<DateNumber> debtEstablishmentEachMonth = new ArrayList<>(); //مبلغ بدهی استقرار به تفکیک ماه
        List<DateNumber> facilityPaidEachMonth = new ArrayList<>(); //مبلغ پرداختی تسهیلات به تفکیک هر ماه
        List<DateNumber> establishmentPaidEachMonth = new ArrayList<>(); //مبلغ پرداختی استقرار به تفکیک هر ماه

        for (Facility facility : unit.getFacilities()) {
            installmentsNumber = installmentsNumber + facility.getRepaymentMonthsNumber();
            loanAmountPerMonth = loanAmountPerMonth + facility.getInstallmentsRepaymentAmount();
            debtInstallmentsArrears = debtInstallmentsArrears + facility.getInstallmentsRepaymentAmount();
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
                auditorService.facilitiesPaid(unit.getFacilities()),
                auditorService.establishmentsPaid(unit.getEstablishments()),
                auditorService.facilitiesDebt(unit.getFacilities()),
                auditorService.establishmentsDebt(unit.getEstablishments()));
    }
}
