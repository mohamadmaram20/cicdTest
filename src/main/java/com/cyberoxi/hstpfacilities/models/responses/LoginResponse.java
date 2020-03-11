package com.cyberoxi.hstpfacilities.models.responses;

import com.cyberoxi.hstpfacilities.models.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    private long id;
    private String name;
    private String type;
    private String avatar;
    private AccessLevel accessLevel;
}
