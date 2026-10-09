package com.pedrofranceschi.orderapi.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("O id buscado não foi encontrado!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
