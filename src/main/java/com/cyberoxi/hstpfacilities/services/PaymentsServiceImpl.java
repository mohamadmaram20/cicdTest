package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.exceptions.BadRequestException;
import com.cyberoxi.hstpfacilities.models.Payment;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentsRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilitiesRepository;
import com.cyberoxi.hstpfacilities.repositories.PaymentsRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 2/2/2020
 */
@Service
public class PaymentsServiceImpl implements PaymentsService {

    private PaymentsRepository paymentsRepository;
    private UnitsRepository unitsRepository;
    private EstablishmentsRepository establishmentsRepository;
    private FacilitiesRepository facilitiesRepository;

    @Autowired
    public PaymentsServiceImpl(PaymentsRepository paymentsRepository, UnitsRepository unitsRepository, EstablishmentsRepository establishmentsRepository, FacilitiesRepository facilitiesRepository) {
        this.paymentsRepository = paymentsRepository;
        this.unitsRepository = unitsRepository;
        this.establishmentsRepository = establishmentsRepository;
        this.facilitiesRepository = facilitiesRepository;
    }

    @Override
    public Payment savePayment(Payment payment) {
        if (unitsRepository.findById(payment.getUnitId()).isPresent() &&
                (establishmentsRepository.findById(payment.getContractId()).isPresent() ||
                        facilitiesRepository.findById(payment.getContractId()).isPresent())){
            return paymentsRepository.save(payment);
        }else {
            // FIXME: 2/2/2020 send some good message
            //throw new BadRequestException("Bad Request");
            return null;
        }
    }
}
