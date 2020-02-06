package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.models.Payment;
import com.cyberoxi.hstpfacilities.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 2/4/20
 */
@Service
public class AuditorServiceImpl implements AuditorService {

    private PaymentsRepository paymentsRepository;

    @Autowired
    public AuditorServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public long establishmentsDebt(Iterable<Establishment> establishments) {
        int debtAmount = 0;
        for (Establishment establishment : establishments)
            debtAmount += establishmentDebt(establishment);
        return debtAmount;
    }

    @Override
    public long establishmentDebt(Establishment establishment) {
        int paidAmount = 0;
        Iterable<Payment> establishmentPayments = paymentsRepository.findAllByContractTypeAndContractIdAndTransactionDateAfter('e', establishment.getId(), establishment.getContractStartDate());
        for (Payment payment : establishmentPayments)
            paidAmount += payment.getAmount();
        Period period = Period.between(LocalDate.parse(establishment.getContractStartDate().toString()), LocalDate.now());
        int passedMonths = period.getYears() * 12 + period.getMonths();
        if (passedMonths >= establishment.getNumberOfMonth())
            passedMonths = establishment.getNumberOfMonth();
        return establishment.getFinalContractMonthlyAmount() * passedMonths - paidAmount;
    }
}
