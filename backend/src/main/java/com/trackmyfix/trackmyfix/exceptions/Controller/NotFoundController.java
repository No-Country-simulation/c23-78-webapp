package com.trackmyfix.trackmyfix.exceptions.Controller;

import com.trackmyfix.trackmyfix.exceptions.BaseErrorResponse;
import com.trackmyfix.trackmyfix.exceptions.ErrorResponse;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundController {

    private final String status = HttpStatus.NOT_FOUND.name();
    private final Integer statusCode = HttpStatus.NOT_FOUND.value();

    @ExceptionHandler(UserNotFoundException.class)
    public BaseErrorResponse handleExpenseException(UserNotFoundException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(status)
                .statusCode(statusCode)
                .build();
    }
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody BaseErrorResponse handleException(Exception ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(status)
                .statusCode(statusCode)
                .build();
    }
}
