package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.Establishment;
import com.cyberoxi.hstpfacilities.repositories.CompaniesRepository;
import com.cyberoxi.hstpfacilities.repositories.EstablishmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/28/2020
 */
@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private CompaniesRepository companiesRepository;
    private EstablishmentsRepository establishmentsRepository;

    @Autowired
    public EstablishmentServiceImpl(CompaniesRepository companiesRepository, EstablishmentsRepository establishmentsRepository) {
        this.companiesRepository = companiesRepository;
        this.establishmentsRepository = establishmentsRepository;
    }

    @Override
    public Iterable<Establishment> getEstablishments(long companyId) {
        return companiesRepository.findById(companyId).get().getEstablishments();
    }

    @Override
    public Establishment getEstablishment(long id) {
        return establishmentsRepository.findById(id).get();
    }

    @Override
    public Company addEstablishment(long companyId, Establishment establishment) {
        Company findCompany = companiesRepository.findById(companyId).get();
        findCompany.getEstablishments().add(establishment);
        return companiesRepository.save(findCompany);
    }

    @Override
    public Establishment updateEstablishment(long id, Establishment establishment) {
        Establishment findEstablishment = establishmentsRepository.findById(id).get();
        // TODO: 1/28/2020 Update fields
        return establishmentsRepository.save(findEstablishment);
    }
}
