package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.components.Generator;
import com.cyberoxi.hstpfacilities.exceptions.BadRequestException;
import com.cyberoxi.hstpfacilities.models.Payment;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilityRepository;
import com.cyberoxi.hstpfacilities.repositories.PaymentRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cyberoxi.hstpfacilities.GlobalVariables.TRACKING_CODE_LENGTH;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 2/2/2020
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private UnitRepository unitRepository;
    private EstablishmentRepository establishmentRepository;
    private FacilityRepository facilityRepository;
    private Generator generator;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, UnitRepository unitRepository, EstablishmentRepository establishmentRepository, FacilityRepository facilityRepository, Generator generator) {
        this.paymentRepository = paymentRepository;
        this.unitRepository = unitRepository;
        this.establishmentRepository = establishmentRepository;
        this.facilityRepository = facilityRepository;
        this.generator = generator;
    }

    @Override
    public Iterable<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Iterable<Payment> getPayments(long unitId) {
        return paymentRepository.findAllByUnitId(unitId);
    }

    @Override
    public Payment getPayment(long id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public Payment savePayment(Payment payment) {
        if (unitRepository.findById(payment.getUnitId()).isPresent() &&
                (establishmentRepository.findById(payment.getContractId()).isPresent() ||
                        facilityRepository.findById(payment.getContractId()).isPresent())) {
            payment.setTrackingCode(generator.generateRandomNumber(TRACKING_CODE_LENGTH));
            return paymentRepository.save(payment);
        } else {
            // FIXME: 2/2/2020 send some good message
            throw new BadRequestException("contractId or unitId not exist");
        }
    }
}
