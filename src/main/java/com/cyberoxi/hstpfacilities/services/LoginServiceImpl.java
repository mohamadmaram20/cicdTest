package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.exceptions.NotAcceptableException;
import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.models.Company;
import com.cyberoxi.hstpfacilities.models.responses.LoginResponse;
import com.cyberoxi.hstpfacilities.repositories.AdminsRepository;
import com.cyberoxi.hstpfacilities.repositories.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Service
public class LoginServiceImpl implements LoginService {

    private AdminsRepository adminsRepository;
    private CompaniesRepository companiesRepository;

    @Autowired
    public LoginServiceImpl(AdminsRepository adminsRepository, CompaniesRepository companiesRepository) {
        this.adminsRepository = adminsRepository;
        this.companiesRepository = companiesRepository;
    }

    @Override
    public LoginResponse login(String username, String password) {
        Optional<Admin> admin = adminsRepository.findByUsernameAndPassword(username, password);
        if (admin.isPresent())
            return new LoginResponse(admin.get().getId(), admin.get().getFirstName() + " " + admin.get().getLastName(), 'a', admin.get().getAvatar(), admin.get().getAccessLevel());
        Optional<Company> company = companiesRepository.findByUsernameAndPassword(username, password);
        if (company.isPresent())
            return new LoginResponse(company.get().getId(), company.get().getName(), 'c', company.get().getAvatar(), 0);
        throw new NotAcceptableException("Username or password incorrect");
    }
}
