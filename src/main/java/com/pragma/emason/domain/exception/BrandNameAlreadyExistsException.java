package com.pragma.emason.domain.exception;

public class BrandNameAlreadyExistsException extends RuntimeException{
    public BrandNameAlreadyExistsException(String message){
        super(message);
    }
}
