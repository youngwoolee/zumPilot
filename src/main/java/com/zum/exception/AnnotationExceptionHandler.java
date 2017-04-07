package com.zum.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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

    Logger logger = LoggerFactory.getLogger(AnnotationExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) {

        logger.debug("defaultErrorHandler()");

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("name",e.getClass().getSimpleName());
        model.addAttribute("message",e.getMessage());

        return "exceptionHandler";
    }

    @ExceptionHandler({ SQLException.class, DataAccessException.class })
    public String databaseError(HttpServletRequest req, Exception e, Model model) {

        logger.debug("databaseError()");

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("name",e.getClass().getSimpleName());
        model.addAttribute("message",e.getMessage());

        return "exceptionHandler";
    }



    @ExceptionHandler(NotFoundExceptionRest.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundException(HttpServletRequest req, NotFoundExceptionRest e, Model model) {

        logger.debug("notFoundException()");

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("name",e.getClass().getSimpleName());
        model.addAttribute("message",e.getDefaultMessage());

        return "exceptionHandler";
    }


    @ExceptionHandler(UserDuplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userDuplicationException(HttpServletRequest req, UserDuplicationException e, Model model) {

        logger.debug("userDuplicationException()");

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("name",e.getClass().getSimpleName());
        model.addAttribute("message",e.getDefaultMessage());

        return "exceptionHandler";
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String authenticationException(HttpServletRequest req, AuthenticationException e, Model model) {

        logger.debug("authenticationException()");

        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("name",e.getClass().getSimpleName());
        model.addAttribute("message",e.getDefaultMessage());

        return "exceptionHandler";
    }






}
