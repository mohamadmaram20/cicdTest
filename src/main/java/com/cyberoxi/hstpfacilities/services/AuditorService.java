package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Establishment;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 2/4/20
 */
public interface AuditorService {

    long establishmentsDebt(Iterable<Establishment> establishments);

    long establishmentDebt(Establishment establishment);
}
