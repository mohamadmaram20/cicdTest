package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.Facility;
import com.cyberoxi.hstpfacilities.repositories.CompaniesRepository;
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

    private CompaniesRepository companiesRepository;
    private FacilitiesRepository facilitiesRepository;

    @Autowired
    public FacilitiesServiceImpl(CompaniesRepository companiesRepository, FacilitiesRepository facilitiesRepository) {
        this.companiesRepository = companiesRepository;
        this.facilitiesRepository = facilitiesRepository;
    }

    @Override
    public Iterable<Facility> getFacilities(long companyId) {
        return companiesRepository.findById(companyId).get().getFacilities();
    }

    @Override
    public Facility getFacility(long id) {
        return facilitiesRepository.findById(id).get();
    }

    @Override
    public Company addFacility(long companyId, Facility facility) {
        Company findCompany = companiesRepository.findById(companyId).get();
        findCompany.getFacilities().add(facility);
        return companiesRepository.save(findCompany);
    }

    @Override
    public Facility updateFacility(long id, Facility facility) {
        Facility findFacility = facilitiesRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return facilitiesRepository.save(findFacility);
    }
}
