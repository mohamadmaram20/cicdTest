package com.cyberoxi.hstpfacilities.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Mohamad Zarei Maram
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 3/10/2020
 */
@Data
@AllArgsConstructor
public class AccessLevel {

    private String title;
    private String role;
    private String[] AllowActions;
    private List<UrlMethod> urlMethods;
}
