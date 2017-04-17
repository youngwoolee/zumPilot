package com.zum.exception;

/**
 * Created by joeylee on 2017-04-06.
 */
public class UserLeaveException extends RuntimeException {
    private String msg;
    private final String defaultMessage = "탈퇴한 사용자입니다.";

    public UserLeaveException(){}

    public UserLeaveException(String msg){
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg + "는 탈퇴한 사용자입니다";
    }

    public String getDefaultMessage(){
        return msg == null ? defaultMessage : msg;
    }
}
