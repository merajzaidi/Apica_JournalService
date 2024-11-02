package com.apica.Jornal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnAuthorizedException extends RuntimeException{
    private final Object response;
    private final HttpStatus httpStatus;

    public UnAuthorizedException(String message) {
        super(message);
        response = null;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public UnAuthorizedException(String message, HttpStatus httpStatus) {
        super(message);
        response = null;
        this.httpStatus = httpStatus;
    }

    public UnAuthorizedException(String message, HttpStatus httpStatus, Object data) {
        super(message);
        response = data;
        this.httpStatus = httpStatus;
    }
}
