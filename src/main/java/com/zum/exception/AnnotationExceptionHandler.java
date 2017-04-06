package com.zum.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


/**
 * Created by joeylee on 2017-04-05.
 */
@ControllerAdvice
public class AnnotationExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {

        ModelAndView mnv = new ModelAndView();

        mnv.addObject("url", req.getRequestURL());
        mnv.addObject("name",e.getClass().getSimpleName());
        mnv.addObject("message",e.getMessage());

        return mnv;
    }

    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public String databaseError(HttpServletRequest req, Exception e) {

        ModelAndView mnv = new ModelAndView();

        mnv.addObject("url", req.getRequestURL());
        mnv.addObject("name",e.getClass().getSimpleName());
        mnv.addObject("message",e.getMessage());

        return "databaseError";
    }



    @ExceptionHandler(NotFoundExceptionRest.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleRuntimeException(HttpServletRequest req, NotFoundExceptionRest e) {
        ModelAndView mnv = new ModelAndView("exceptionHandler");
        mnv.addObject("url", req.getRequestURL());
        mnv.addObject("name",e.getClass().getSimpleName());
        mnv.addObject("message",e.getDefaultMessage());

        return mnv;
    }






}
