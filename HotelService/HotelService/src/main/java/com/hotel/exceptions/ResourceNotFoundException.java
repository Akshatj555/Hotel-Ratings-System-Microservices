package com.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not available for this");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
