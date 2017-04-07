package com.zum.exception;

/**
 * Created by joeylee on 2017-04-07.
 */
public class UserDuplicationException extends RuntimeException {

    private String msg;
    private final String defaultMessage = "중복된 아이디입니다.";

    public UserDuplicationException(){}

    public UserDuplicationException(String msg){
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
