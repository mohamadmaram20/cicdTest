package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@Service
public class FacilityServiceImpl implements FacilityService {

    private UnitRepository unitRepository;
    private FacilityRepository facilityRepository;

    @Autowired
    public FacilityServiceImpl(UnitRepository unitRepository, FacilityRepository facilityRepository) {
        this.unitRepository = unitRepository;
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Iterable<Facility> getFacilities(long unitId) {
        return unitRepository.findById(unitId).get().getFacilities();
    }

    @Override
    public Facility getFacility(long id) {
        return facilityRepository.findById(id).get();
    }

    @Override
    public Unit addFacility(long unitId, Facility facility) {
        Unit findUnit = unitRepository.findById(unitId).get();
        findUnit.getFacilities().add(facility);
        return unitRepository.save(findUnit);
    }

    @Override
    public Facility updateFacility(long id, Facility facility) {
        Facility findFacility = facilityRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return facilityRepository.save(findFacility);
    }
}
