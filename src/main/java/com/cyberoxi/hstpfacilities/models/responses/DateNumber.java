package com.cyberoxi.hstpfacilities.models.responses;

import lombok.Data;

@Data
public class DateNumber {

    private String date;
    private long number;

    public DateNumber(String date, long number) {
        this.date = date;
        this.number = number;
    }
}
