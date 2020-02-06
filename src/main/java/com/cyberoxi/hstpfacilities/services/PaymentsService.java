package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Payment;


/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 2/2/2020
 */
public interface PaymentsService {

    Iterable<Payment> getPayments();

    Iterable<Payment> getPayments(long unitId);

    Payment getPayment(long id);

    Payment savePayment(Payment payment);
}
