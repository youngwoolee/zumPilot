package com.zum.exception;

/**
 * Created by joeylee on 2017-04-06.
 */
public class UserLeaveException extends RuntimeException {
    public UserLeaveException(String userName) {
        super(userName + "not found!!");
    }
}
