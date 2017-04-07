package com.zum.exception;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

/**
 * Created by joeylee on 2017-04-06.
 */



public class NotFoundExceptionRest extends RuntimeException {

    private String msg;
    private final String defaultMessage = "페이지를 찾을 수 없습니다.";

    public NotFoundExceptionRest(){}

    public NotFoundExceptionRest(String msg){
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
