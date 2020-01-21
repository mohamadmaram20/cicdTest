package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.Admin;
import com.cyberoxi.hstpfacilities.models.responses.AdminInformation;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
public interface AdminsService {

    AdminInformation getAdminInformation();

    Admin adminLogin(String username, String password);
}
