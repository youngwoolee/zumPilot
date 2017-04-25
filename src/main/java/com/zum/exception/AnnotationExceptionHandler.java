package com.zum.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by joeylee on 2017-04-05.
 */
@ControllerAdvice
public class AnnotationExceptionHandler {

    Logger logger = LoggerFactory.getLogger(AnnotationExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) {

        return errorHandlerModelAttribute(req, e, model, "잘못된 접근입니다");
    }

    @ExceptionHandler(IOException.class)
    public String iOException(HttpServletRequest req, IOException e, Model model) {

        return errorHandlerModelAttribute(req, e, model, "잘못된 파일 형식입니다");
    }

    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public String databaseError(HttpServletRequest req, Exception e, Model model) {

        return errorHandlerModelAttribute(req, e, model, "디비 문제입니다");
    }

    @ExceptionHandler(NotFoundExceptionRest.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundException(HttpServletRequest req, NotFoundExceptionRest e, Model model) {

        return errorHandlerModelAttribute(req, e, model, e.getDefaultMessage());
    }

    @ExceptionHandler(UserDuplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userDuplicationException(HttpServletRequest req, UserDuplicationException e, Model model) {

        return errorHandlerModelAttribute(req, e, model, e.getDefaultMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String authenticationException(HttpServletRequest req, AuthenticationException e, Model model) {

        return errorHandlerModelAttribute(req, e, model, e.getDefaultMessage());
    }

    @ExceptionHandler(UserLeaveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userLeaveException(HttpServletRequest req, UserLeaveException e, Model model) {

        return errorHandlerModelAttribute(req, e, model, e.getMessage());
    }

    private String errorHandlerModelAttribute(HttpServletRequest req, Exception e, Model model, String attributeValue) {
        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("name", e.getClass().getSimpleName());
        model.addAttribute("message", attributeValue);

        return "exceptionHandler";
    }

}
