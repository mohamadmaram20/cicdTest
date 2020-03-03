package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Facility;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
public interface FacilityService {

    Iterable<Facility> getFacilities(long unitId);

    Facility getFacility(long id);

    Unit addFacility(long unitId, Facility facility);

    Facility updateFacility(long id, Facility facility);
}
