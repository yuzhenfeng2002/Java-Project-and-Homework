package com.Yuzhen.eRestaurant;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandleController {
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        String message = e.getMessage();
        model.addAttribute("mymessage", message);
        return "myError";
    }
}