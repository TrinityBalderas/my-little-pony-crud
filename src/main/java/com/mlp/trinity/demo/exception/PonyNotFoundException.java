package com.mlp.trinity.demo.exception;

public class PonyNotFoundException extends RuntimeException{
    public PonyNotFoundException(String message){
        super(message);
    }
}
