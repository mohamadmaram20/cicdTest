package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.exceptions.BadRequestException;
import com.cyberoxi.hstpfacilities.models.Credential;
import com.cyberoxi.hstpfacilities.repositories.CredentialRepository;
import com.cyberoxi.hstpfacilities.repositories.UserRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 1/30/2020
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private CredentialRepository credentialRepository;

    public UserDetailsServiceImpl(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) {
        User admin = adminRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Username or password is wrong"));
        return new Credential(admin.getUsername(), admin.getPassword(), emptyList());
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) {

        if (credentialRepository.findByUsername(username).isPresent()) {
            Credential credential = credentialRepository.findByUsername(username).get();
            return new org.springframework.security.core.userdetails.User(credential.getUsername(), credential.getPassword(), Collections.singleton(new SimpleGrantedAuthority(credential.getRole())));
        } else
            throw new BadRequestException("Username or password is wrong");

        /*if (userRepository.findByCredential_Username(username).isPresent()) {
            User user = userRepository.findByCredential_Username(username).get();
            return new org.springframework.security.core.userdetails.User(user.getCredential().getUsername(), user.getCredential().getPassword(),);
        } else if (unitRepository.findByCredential_Username(username).isPresent()) {
            Unit unit = unitRepository.findByCredential_Username(username).get();
            return new org.springframework.security.core.userdetails.User(unit.getCredential().getUsername(), unit.getCredential().getPassword(), emptyList());
        } else {
            throw new BadRequestException("Username or password is wrong");
        }*/
    }
}
