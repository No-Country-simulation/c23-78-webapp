package com.trackmyfix.trackmyfix.exceptions.Controller;

import com.trackmyfix.trackmyfix.exceptions.BaseErrorResponse;
import com.trackmyfix.trackmyfix.exceptions.ErrorResponse;
import com.trackmyfix.trackmyfix.exceptions.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    private final String status = HttpStatus.BAD_REQUEST.name();
    private final Integer statusCode = HttpStatus.BAD_REQUEST.value();

    @ExceptionHandler(SQLException.class)
    public BaseErrorResponse handleValidationExceptions(SQLException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(status)
                .statusCode(statusCode)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<String>();
        ex.getAllErrors()
                .forEach(error->errors.add(error.getDefaultMessage()));
        return ErrorsResponse.builder()
                .errors(errors)
                .status(status)
                .statusCode(statusCode)
                .build();
    }
}

