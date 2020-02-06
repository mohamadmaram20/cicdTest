package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.components.Generator;
import com.cyberoxi.hstpfacilities.exceptions.BadRequestException;
import com.cyberoxi.hstpfacilities.models.Payment;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentsRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilitiesRepository;
import com.cyberoxi.hstpfacilities.repositories.PaymentsRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cyberoxi.hstpfacilities.GlobalVariables.TRACKING_CODE_LENGTH;

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
    private Generator generator;

    @Autowired
    public PaymentsServiceImpl(PaymentsRepository paymentsRepository, UnitsRepository unitsRepository, EstablishmentsRepository establishmentsRepository, FacilitiesRepository facilitiesRepository, Generator generator) {
        this.paymentsRepository = paymentsRepository;
        this.unitsRepository = unitsRepository;
        this.establishmentsRepository = establishmentsRepository;
        this.facilitiesRepository = facilitiesRepository;
        this.generator = generator;
    }

    @Override
    public Iterable<Payment> getPayments() {
        return paymentsRepository.findAll();
    }

    @Override
    public Iterable<Payment> getPayments(long unitId) {
        return paymentsRepository.findAllByUnitId(unitId);
    }

    @Override
    public Payment getPayment(long id) {
        return paymentsRepository.findById(id).get();
    }

    @Override
    public Payment savePayment(Payment payment) {
        if (unitsRepository.findById(payment.getUnitId()).isPresent() &&
                (establishmentsRepository.findById(payment.getContractId()).isPresent() ||
                        facilitiesRepository.findById(payment.getContractId()).isPresent())) {
            payment.setTrackingCode(generator.generateRandomNumber(TRACKING_CODE_LENGTH));
            return paymentsRepository.save(payment);
        } else {
            // FIXME: 2/2/2020 send some good message
            throw new BadRequestException("contractId or unitId not exist");
        }
    }
}
