package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.responses.CompanyBrief;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/22/2020
 */
public interface CompaniesService {

    Iterable<Company> getCompanies();

    Iterable<CompanyBrief> getCompaniesBrief();

    Company getCompany(long id);

    Company saveCompany(Company company);

    Company updateCompany(long id, Company company);
}
