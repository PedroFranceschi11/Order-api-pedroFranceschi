package com.pedrofranceschi.orderapi.exceptions;

public class ResourceNotFoundHandler extends RuntimeException {

    public ResourceNotFoundHandler(){
        super("O id buscado não foi encontrado!");
    }

    public ResourceNotFoundHandler(String message){
        super(message);
    }
}
