package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.models.Facility;

import java.util.List;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 2/4/20
 */
public interface AuditorService {

    long establishmentsDebt(List<Establishment> establishments);

    long establishmentDebt(Establishment establishment);

    long establishmentsPaid(List<Establishment> establishments);

    long establishmentPaid(Establishment establishment);

    long facilitiesDebt(List<Facility> facilities);

    long facilityDebt(Facility facility);

    long facilitiesPaid(List<Facility> facilities);

    long facilityPaid(Facility facility);
}
