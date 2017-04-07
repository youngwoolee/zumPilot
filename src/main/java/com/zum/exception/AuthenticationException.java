package com.zum.exception;

/**
 * Created by joeylee on 2017-04-07.
 */
public class AuthenticationException extends RuntimeException {

    private String msg;
    private final String defaultMessage = "해당 권한이 없습니다.";

    public AuthenticationException(){}

    public AuthenticationException(String msg){
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    public String getDefaultMessage(){
        return msg == null ? defaultMessage : msg;
    }
}
