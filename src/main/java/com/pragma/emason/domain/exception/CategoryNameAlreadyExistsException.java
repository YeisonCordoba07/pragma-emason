package com.pragma.emason.domain.exception;

public class CategoryNameAlreadyExistsException extends RuntimeException{
    public CategoryNameAlreadyExistsException(String message){
        super(message);
    }
}