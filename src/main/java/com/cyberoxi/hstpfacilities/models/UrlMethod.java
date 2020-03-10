package com.cyberoxi.hstpfacilities.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mohamad Zarei Maram
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 3/10/2020
 */
@Data
@AllArgsConstructor
public class UrlMethod {

    private String url;
    private String[] methods;
}
