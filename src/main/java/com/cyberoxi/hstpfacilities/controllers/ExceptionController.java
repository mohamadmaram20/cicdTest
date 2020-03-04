package com.cyberoxi.hstpfacilities.controllers;

import com.cyberoxi.hstpfacilities.exceptions.BadRequestException;
import com.cyberoxi.hstpfacilities.exceptions.NotAcceptableException;
import com.cyberoxi.hstpfacilities.models.responses.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

/**
 * @author Mohammad Zarei Maram
 * @version 0.0.1
 * @since 1/1/20
 * <p>
 * List of exception that we can handle
 * BindException	                        400 (Bad Request)
 * ConversionNotSupportedException	        500 (Internal Server Error)
 * HttpMediaTypeNotAcceptableException	    406 (Not Acceptable)
 * HttpMediaTypeNotSupportedException	    415 (Unsupported Media Type)
 * HttpMessageNotReadableException	        400 (Bad Request)
 * HttpMessageNotWritableException	        500 (Internal Server Error)
 * HttpRequestMethodNotSupportedException	405 (Method Not Allowed)
 * MethodArgumentNotValidException	        400 (Bad Request)
 * MissingServletRequestParameterException	400 (Bad Request)
 * MissingServletRequestPartException	    400 (Bad Request)
 * NoSuchRequestHandlingMethodException     404 (Not Found)
 * TypeMismatchException	                400 (Bad Request)
 */

@Slf4j
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoSuchElementException.class, DataIntegrityViolationException.class, InvalidDataAccessApiUsageException.class})
    public ResponseEntity<?> notFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<?> notAcceptableException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> authenticationException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }
}
