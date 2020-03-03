package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.exceptions.NotAcceptableException;
import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.LoginResponse;
import com.cyberoxi.hstpfacilities.repositories.AdminRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Service
public class LoginServiceImpl implements LoginService {

    private AdminRepository adminRepository;
    private UnitRepository unitRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginServiceImpl(AdminRepository adminRepository, UnitRepository unitRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.unitRepository = unitRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public LoginResponse login(String username, String password) {
        /*Optional<Admin> admin = adminsRepository.findByUsername(username);
        if (bCryptPasswordEncoder.matches(password , admin.get().getPassword())){}*/
        // FIXME: 3/2/2020 because of using BCriptPasswordEncoding we can not use this method
        Optional<Admin> admin = adminRepository.findByUsernameAndPassword(username, password);
        if (admin.isPresent())
            return new LoginResponse(admin.get().getId(), admin.get().getFirstName() + " " + admin.get().getLastName(), 'a', admin.get().getAvatar(), admin.get().getAccessLevel());
        Optional<Unit> unit = unitRepository.findByUsernameAndPassword(username, password);
        if (unit.isPresent())
            return new LoginResponse(unit.get().getId(), unit.get().getName(), 'c', unit.get().getAvatar(), 0);
        throw new NotAcceptableException("Username or password incorrect");
    }
}
