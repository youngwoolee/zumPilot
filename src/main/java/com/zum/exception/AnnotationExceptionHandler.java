package com.zum.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by joeylee on 2017-04-05.
 */
@ControllerAdvice
public class AnnotationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        System.out.println("exception");
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException e) {
        ModelAndView mnv = new ModelAndView("exceptionHandler");
        mnv.addObject("name",e.getClass().getSimpleName());
        mnv.addObject("message",e.getMessage());

        return mnv;
    }
}
