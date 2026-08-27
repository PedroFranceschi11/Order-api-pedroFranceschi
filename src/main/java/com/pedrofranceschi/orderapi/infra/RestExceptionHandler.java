package com.pedrofranceschi.orderapi.infra;

import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundHandler.class)
    public ResponseEntity<RestErrorMessage>ResourceNotFoundHandler(ResourceNotFoundHandler exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND.value(),  exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
