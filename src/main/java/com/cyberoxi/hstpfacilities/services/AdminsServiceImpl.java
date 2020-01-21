package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.models.responses.AdminInformation;
import com.cyberoxi.hstpfacilities.repositories.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/9/20
 */
@Service
public class AdminsServiceImpl implements AdminsService {

    private AdminsRepository adminsRepository;

    @Autowired
    public AdminsServiceImpl(AdminsRepository adminsRepository) {
        this.adminsRepository = adminsRepository;
    }

    @Override
    public AdminInformation getAdminInformation() {
        return new AdminInformation(0);
    }

    @Override
    public Admin adminLogin(String username, String password) {
        return adminsRepository.findByUsernameAndPassword(username, password).get();
    }
}
