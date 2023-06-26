package com.opodo.exception;

import org.springframework.http.HttpStatus;
//If post is not found then given this exception.
public class BlogAPIException extends RuntimeException{
    private HttpStatus status;            //two parameters give here.
    private String message;
    public BlogAPIException(HttpStatus status, String message) {         //setter
        super(message);
        this.status = status;
        this.message = message;

    }
    public HttpStatus getStatus(){                                      //getter
        return status;
    }
    @Override
    public String getMessage(){
        return message;
    }
}









