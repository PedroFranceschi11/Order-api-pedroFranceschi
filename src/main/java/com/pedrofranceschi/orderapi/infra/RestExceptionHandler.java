package com.pedrofranceschi.orderapi.infra;

import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundHandler.class)
    public ResponseEntity<RestErrorMessage>ResourceNotFoundHandler(ResourceNotFoundHandler exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND.value(),  exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, IllegalArgumentException.class})
    public ResponseEntity<RestErrorMessage> handleEnumOuTipoInvalido(Exception exception) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Categoria ou argumento informado é inválido!"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
