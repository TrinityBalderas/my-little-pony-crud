package com.mlp.trinity.demo.exception;

public class PonyNameAlreadyExists extends RuntimeException {
    public PonyNameAlreadyExists(String message) {
        super(message);
    }
}
