package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.User;
import com.cyberoxi.hstpfacilities.models.responses.AdminInformation;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
public interface AdminService {

    AdminInformation getAdminInformation();

    User save(User user);
}
