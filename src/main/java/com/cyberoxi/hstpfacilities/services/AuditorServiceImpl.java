package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.models.Payment;
import com.cyberoxi.hstpfacilities.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 2/4/20
 */
@Service
public class AuditorServiceImpl implements AuditorService {

    private PaymentRepository paymentRepository;

    @Autowired
    public AuditorServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public long establishmentsDebt(List<Establishment> establishments) {
        int debtAmount = 0;
        for (Establishment establishment : establishments)
            debtAmount += establishmentDebt(establishment);
        return debtAmount;
    }

    @Override
    public long establishmentDebt(Establishment establishment) {
        Period period = Period.between(LocalDate.parse(establishment.getContractStartDate().toString()), LocalDate.now());
        int passedMonths = period.getYears() * 12 + period.getMonths();
        if (passedMonths == 0)
            return 0;
        if (passedMonths >= establishment.getNumberOfMonth())
            passedMonths = establishment.getNumberOfMonth();
        return establishment.getFinalContractMonthlyAmount() * passedMonths - establishmentPaid(establishment);
    }

    @Override
    public long establishmentsPaid(List<Establishment> establishments) {
        int paidAmount = 0;
        for (Establishment establishment : establishments)
            paidAmount += establishmentPaid(establishment);
        return paidAmount;
    }

    @Override
    public long establishmentPaid(Establishment establishment) {
        // TODO: 3/12/2020 dateAfterEqual and check for payment(mmk)
        int paidAmount = 0;
        Iterable<Payment> establishmentPayments = paymentRepository.findAllByContractTypeAndContractIdAndTransactionDateAfter('e', establishment.getId(), establishment.getContractStartDate());
        for (Payment payment : establishmentPayments)
            paidAmount += payment.getAmount();
        return paidAmount;
    }

    @Override
    public long facilitiesDebt(List<Facility> facilities) {
        int debtAmount = 0;
        for (Facility facility : facilities)
            debtAmount += facilityDebt(facility);
        return debtAmount;
    }

    @Override
    public long facilityDebt(Facility facility) {
        Period period = Period.between(LocalDate.parse(facility.getInstallmentsStartDate().toString()), LocalDate.now());
        int passedMonths = period.getYears() * 12 + period.getMonths();
        if (passedMonths == 0)
            return 0;
        if (passedMonths >= facility.getRepaymentMonthsNumber())
            passedMonths = facility.getRepaymentMonthsNumber();
        return facility.getMonthlyRepaymentAmount() * passedMonths - facilityPaid(facility);
    }

    @Override
    public long facilitiesPaid(List<Facility> facilities) {
        int paidAmount = 0;
        for (Facility facility : facilities)
            paidAmount += facilityPaid(facility);
        return paidAmount;
    }

    @Override
    public long facilityPaid(Facility facility) {
        int paidAmount = 0;
        Iterable<Payment> facilityPayments = paymentRepository.findAllByContractTypeAndContractIdAndTransactionDateAfter('f', facility.getId(), facility.getInstallmentsStartDate());
        for (Payment payment : facilityPayments)
            paidAmount += payment.getAmount();
        return paidAmount;
    }
}
