package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.components.Accesses;
import com.cyberoxi.hstpfacilities.exceptions.NotAcceptableException;
import com.cyberoxi.hstpfacilities.models.User;
import com.cyberoxi.hstpfacilities.models.Unit;
import com.cyberoxi.hstpfacilities.models.responses.LoginResponse;
import com.cyberoxi.hstpfacilities.repositories.UserRepository;
import com.cyberoxi.hstpfacilities.repositories.UnitRepository;
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

    private UserRepository userRepository;
    private UnitRepository unitRepository;
    private Accesses accesses;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, UnitRepository unitRepository, Accesses accesses) {
        this.userRepository = userRepository;
        this.unitRepository = unitRepository;
        this.accesses = accesses;
    }

    /*@Override
    public LoginResponse login(String username, String password) {
        *//*Optional<User> admin = adminsRepository.findByUsername(username);
        if (bCryptPasswordEncoder.matches(password , admin.get().getPassword())){}*//*
        // FIXME: 3/2/2020 because of using BCriptPasswordEncoding we can not use this method
        Optional<User> admin = userRepository.findByCredential_UsernameAndCredential_Password(username, password);
        if (admin.isPresent())
            return new LoginResponse(admin.get().getId(), admin.get().getFirstName() + " " + admin.get().getLastName(), 'a', admin.get().getAvatar(), admin.get().getCredential().getRole());
        Optional<Unit> unit = unitRepository.findByCredential_UsernameAndCredential_Password(username, password);
        if (unit.isPresent())
            return new LoginResponse(unit.get().getId(), unit.get().getName(), 'c', unit.get().getAvatar(), unit.get().getCredential().getRole());
        throw new NotAcceptableException("Username or password incorrect");
    }*/

    @Override
    public LoginResponse loginByUsernameAndRole(String username, String role) {

        if (role.startsWith("A")) {
            Optional<User> user = userRepository.findByCredential_Username(username);
            return new LoginResponse(user.get().getId(), user.get().getFirstName() + " " + user.get().getLastName(), 'a', user.get().getAvatar(), accesses.getAccessLevel(user.get().getCredential().getRole()));
        } else if (role.startsWith("C")) {
            Optional<Unit> unit = unitRepository.findByCredential_Username(username);
            return new LoginResponse(unit.get().getId(), unit.get().getName(), 'c', unit.get().getAvatar(), accesses.getAccessLevel(unit.get().getCredential().getRole()));
        }
        throw new NotAcceptableException("Username or password incorrect");

    }

}
