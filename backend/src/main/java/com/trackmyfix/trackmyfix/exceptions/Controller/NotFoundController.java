package com.trackmyfix.trackmyfix.exceptions.Controller;

import com.trackmyfix.trackmyfix.exceptions.BaseErrorResponse;
import com.trackmyfix.trackmyfix.exceptions.ErrorResponse;
import com.trackmyfix.trackmyfix.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundController {

    private final String status = HttpStatus.BAD_REQUEST.name();
    private final Integer statusCode = HttpStatus.BAD_REQUEST.value();

    @ExceptionHandler(UserNotFoundException.class)
    public BaseErrorResponse handleExpenseException(UserNotFoundException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(status)
                .statusCode(statusCode)
                .build();
    }
}
