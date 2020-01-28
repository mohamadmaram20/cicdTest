package com.cyberoxi.hstpfacilities.models.requests;

import lombok.Data;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Data
public class LoginRequest {

    private String username;
    private String password;
}
