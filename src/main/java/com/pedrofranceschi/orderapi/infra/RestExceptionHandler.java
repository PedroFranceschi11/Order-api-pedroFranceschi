package com.pedrofranceschi.orderapi.infra;

import com.pedrofranceschi.orderapi.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleResourceNotFound(ResourceNotFoundException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND.value(),  exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestErrorMessage> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "O valor: " + exception.getValue() + " é inválido para o parametro " + exception.getName()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestErrorMessage> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        RestErrorMessage response = new RestErrorMessage (HttpStatus.BAD_REQUEST.value(), "Corpo da requisição ausente ou inválido. Verifique o JSON enviado.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), "Dados invalidos na requisição.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestErrorMessage>handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.CONFLICT.value(), "Operação não permitida: o registro está em uso ou já existe.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage>handleInternalServerError(Exception exception) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor."
        );
        log.error("Erro inesperado", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<RestErrorMessage> handleNoResourceFound(NoResourceFoundException exception) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                "Rota inexistente: " + exception.getResourcePath()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestErrorMessage> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                "O método: " + exception.getMethod() + " não é suportado para esta rota. metodos suportados para esta rota: " + Arrays.toString(exception.getSupportedMethods()));
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<RestErrorMessage> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                "O tipo de mídia enviado não é suportado. Certifique-se de definir o cabeçalho 'Content-Type' como 'application/json'."
        );
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestErrorMessage> handletIllegalArgument(IllegalArgumentException exception) {
        RestErrorMessage response = new RestErrorMessage(
                HttpStatus.BAD_REQUEST.value(), "Valor fornecido é inválido para este parâmetro. Verifique as opções permitidas."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
