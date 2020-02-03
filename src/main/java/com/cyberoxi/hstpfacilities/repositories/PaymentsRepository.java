package com.cyberoxi.hstpfacilities.repositories;

import com.cyberoxi.hstpfacilities.models.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 2/2/2020
 */
@Repository
public interface PaymentsRepository extends CrudRepository<Payment, Long> {

    Iterable<Payment> findAllByUnitId(long unitId);
}
