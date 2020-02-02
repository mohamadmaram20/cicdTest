package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.exceptions.NotAcceptableException;
import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.LoginResponse;
import com.cyberoxi.hstpfacilities.repositories.AdminsRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitsRepository;
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
    private UnitsRepository unitsRepository;

    @Autowired
    public LoginServiceImpl(AdminsRepository adminsRepository, UnitsRepository unitsRepository) {
        this.adminsRepository = adminsRepository;
        this.unitsRepository = unitsRepository;
    }

    @Override
    public LoginResponse login(String username, String password) {
        Optional<Admin> admin = adminsRepository.findByUsernameAndPassword(username, password);
        if (admin.isPresent())
            return new LoginResponse(admin.get().getId(), admin.get().getFirstName() + " " + admin.get().getLastName(), 'a', admin.get().getAvatar(), admin.get().getAccessLevel());
        Optional<Unit> unit = unitsRepository.findByUsernameAndPassword(username, password);
        if (unit.isPresent())
            return new LoginResponse(unit.get().getId(), unit.get().getName(), 'c', unit.get().getAvatar(), 0);
        throw new NotAcceptableException("Username or password incorrect");
    }
}
