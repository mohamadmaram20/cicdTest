package com.cyberoxi.hstpfacilities.services;

import com.cyberoxi.hstpfacilities.models.responses.LoginResponse;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
public interface LoginService {

    LoginResponse login(String username, String password);

    LoginResponse login(String username);
}
