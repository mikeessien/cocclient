package com.essienmichael.cocclient.exception;

public class ClientInvalidPasswordException extends RuntimeException{
    public ClientInvalidPasswordException(String message) {
        super(message);
    }

    public ClientInvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
