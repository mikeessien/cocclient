package com.essienmichael.cocclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(value = {ClientNotFoundException.class})
    public ResponseEntity<ClientException> handleClientNotFoundException(ClientNotFoundException clientNotFoundException){
        ClientException clientException = new ClientException();
        clientException.setMessage(clientNotFoundException.getMessage());
        clientException.setHttpStatus(HttpStatus.NOT_FOUND);
        clientException.setTimestamp(ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(clientException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ClientBadRequestException.class})
    public ResponseEntity<ClientException> handleClientAlreadyExistException(ClientBadRequestException clientBadRequestException){
        ClientException clientException = new ClientException(
                clientBadRequestException.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(clientException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ClientInvalidPasswordException.class})
    public ResponseEntity<ClientException> handleClientInvalidPasswordException(ClientInvalidPasswordException clientInvalidPasswordException){
        ClientException clientException = new ClientException(
                clientInvalidPasswordException.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(clientException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RoleNotFoundException.class})
    public ResponseEntity<ClientException> handleRoleNotFoundException(RoleNotFoundException roleNotFoundException){
        ClientException clientException = new ClientException(
                roleNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(clientException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DepartmentNotFoundException.class})
    public ResponseEntity<ClientException> handleDepartmentNotFoundException(DepartmentNotFoundException departmentNotFoundException){
        ClientException clientException = new ClientException(
                departmentNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(clientException, HttpStatus.NOT_FOUND);
    }
}
