package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.Facility;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
public interface FacilitiesService {

    Iterable<Facility> getFacilities(long companyId);

    Facility getFacility(long id);

    Company addFacility(long companyId, Facility facility);

    Facility updateFacility(long id, Facility facility);
}
