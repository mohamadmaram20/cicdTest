package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.responses.AdminInformation;
import com.cyberoxi.hstpfacilities.repositories.CompaniesRepository;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentsRepository;
import com.cyberoxi.hstpfacilities.repositories.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/9/20
 */
@Service
public class AdminsServiceImpl implements AdminsService {

    private CompaniesRepository companiesRepository;
    private FacilitiesRepository facilitiesRepository;
    private EstablishmentsRepository establishmentsRepository;

    @Autowired
    public AdminsServiceImpl(CompaniesRepository companiesRepository, FacilitiesRepository facilitiesRepository, EstablishmentsRepository establishmentsRepository) {
        this.companiesRepository = companiesRepository;
        this.facilitiesRepository = facilitiesRepository;
        this.establishmentsRepository = establishmentsRepository;
    }

    @Override
    public AdminInformation getAdminInformation() {
        return new AdminInformation(companiesRepository.count(), facilitiesRepository.count(), establishmentsRepository.count());
    }
}
