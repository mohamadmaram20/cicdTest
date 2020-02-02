package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@Service
public class FacilitiesServiceImpl implements FacilitiesService {

    private UnitsRepository unitsRepository;
    private FacilitiesRepository facilitiesRepository;

    @Autowired
    public FacilitiesServiceImpl(UnitsRepository unitsRepository, FacilitiesRepository facilitiesRepository) {
        this.unitsRepository = unitsRepository;
        this.facilitiesRepository = facilitiesRepository;
    }

    @Override
    public Iterable<Facility> getFacilities(long unitId) {
        return unitsRepository.findById(unitId).get().getFacilities();
    }

    @Override
    public Facility getFacility(long id) {
        return facilitiesRepository.findById(id).get();
    }

    @Override
    public Unit addFacility(long unitId, Facility facility) {
        Unit findUnit = unitsRepository.findById(unitId).get();
        findUnit.getFacilities().add(facility);
        return unitsRepository.save(findUnit);
    }

    @Override
    public Facility updateFacility(long id, Facility facility) {
        Facility findFacility = facilitiesRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return facilitiesRepository.save(findFacility);
    }
}
