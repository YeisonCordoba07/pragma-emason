package com.pragma.emason.domain.exception;

public class BrandNameAlreadyExists  extends RuntimeException{
    public BrandNameAlreadyExists(String message){
        super(message);
    }
}
