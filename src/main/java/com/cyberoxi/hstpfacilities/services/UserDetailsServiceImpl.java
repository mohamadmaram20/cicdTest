package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.repositories.AdminRepository;
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

    public UserDetailsServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username).get();
        return new User(admin.getUsername(), admin.getPassword(), emptyList());
    }
}
