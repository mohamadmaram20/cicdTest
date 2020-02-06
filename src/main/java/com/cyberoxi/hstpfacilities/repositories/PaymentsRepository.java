package com.cyberoxi.hstpfacilities.repositories;

import com.cyberoxi.hstpfacilities.models.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 2/2/2020
 */
@Repository
public interface PaymentsRepository extends CrudRepository<Payment, Long> {

    Iterable<Payment> findAllByUnitId(long unitId);

    Iterable<Payment> findAllByContractTypeAndContractIdAndTransactionDateAfter(char contractType, long contractId, Date transactionDateAfter);
}
