package com.threedumbdevs.springapi.exceptions;

public class InternalErrorException extends RuntimeException{
    public InternalErrorException(String message) {
        super(message);
    }
}
