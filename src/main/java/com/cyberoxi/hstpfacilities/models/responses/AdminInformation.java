package com.cyberoxi.hstpfacilities.models.responses;

import lombok.Data;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 1/21/20
 */
@Data
public class AdminInformation {

    private long users;

    public AdminInformation(long users) {
        this.users = users;
    }
}
