package com.essienmichael.cocclient.exception;

public class ClientBadRequestException extends RuntimeException{
    public ClientBadRequestException(String message) {
        super(message);
    }

    public ClientBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
