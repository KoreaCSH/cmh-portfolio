package com.choimyeongheon.portfolio.global.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        String message = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.setViewName("exception/error");

        return mav;
    }

    @ExceptionHandler(CustomException.class)
    public ModelAndView exceptionHandler(CustomException e) {

        String message = e.getMessage();

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", message);
        mav.setViewName("exception/error");
        return mav;
    }

}
