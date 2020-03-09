package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.exceptions.BadRequestException;
import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.repositories.AdminRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/30/2020
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AdminRepository adminRepository;
    private UnitRepository unitRepository;

    public UserDetailsServiceImpl(AdminRepository adminRepository, UnitRepository unitRepository) {
        this.adminRepository = adminRepository;
        this.unitRepository = unitRepository;
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Username or password is wrong"));
        return new User(admin.getUsername(), admin.getPassword(), emptyList());
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (adminRepository.findByUsername(username).isPresent()) {
            Admin admin = adminRepository.findByUsername(username).get();
            return new User(admin.getUsername(), admin.getPassword(), emptyList());
        } else if (unitRepository.findByUsername(username).isPresent()) {
            Unit unit = unitRepository.findByUsername(username).get();
            return new User(unit.getUsername(), unit.getPassword(), emptyList());
        } else {
            throw new BadRequestException("Username or password is wrong");
        }
    }
}
