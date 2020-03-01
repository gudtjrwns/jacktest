package com.example.jacktest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 공통 예외 처리
    @ExceptionHandler(Exception.class)
    public ModelAndView common(Exception e) {

        LOGGER.info(e.toString());

        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", e);
        mav.setViewName("exceptions/error_common");

        return mav;    
    }
}
