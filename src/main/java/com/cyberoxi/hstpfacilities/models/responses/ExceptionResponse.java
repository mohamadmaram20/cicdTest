package com.cyberoxi.hstpfacilities.models.responses;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Zarei Maram
 * @version 0.0.1
 * @since 1/21/20
 */
@Data
public class ExceptionResponse {

    private int status;
    private List<String> messages = new ArrayList<>();

    public ExceptionResponse(int status) {
        this.status = status;
    }

    public ExceptionResponse(int status, List<String> messages) {
        this.status = status;
        this.messages = messages;
    }

    public ExceptionResponse(int status, String message) {
        this.status = status;
        this.messages.add(message);
    }

    public ExceptionResponse(int status, BindingResult bindingResult) {
        this.status = status;
        for (Object object : bindingResult.getAllErrors())
            messages.add(((FieldError) object).getDefaultMessage());
    }
}
