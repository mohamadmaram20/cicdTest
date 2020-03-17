package com.cyberoxi.hstpfacilities.models;

import lombok.Data;

/**
 * @author Mohammad Mahdi Kahool
 * @version 0.0.1
 * @since 3/16/20
 */
@Data
public class AString {

    private String string;

    public AString() {
    }

    public AString(String string) {
        this.string = string;
    }
}
