package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.repositories.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/22/2020
 */
@Service
public class CompaniesServiceImpl implements CompaniesService {

    private CompaniesRepository companiesRepository;

    @Autowired
    public CompaniesServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public Iterable<Company> getCompanies() {
        return companiesRepository.findAll();
    }

    @Override
    public Company getCompany(long id) {
        return companiesRepository.findById(id).get();
    }

    @Override
    public Company saveCompany(Company company) {
        return companiesRepository.save(company);
    }

    @Override
    public Company updateCompany(long id, Company company) {
        Company findCompany = companiesRepository.findById(id).get();
        // TODO update fields
        return companiesRepository.save(findCompany);
    }
}
