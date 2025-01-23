package com.trackmyfix.trackmyfix.exceptions.Controller;

import com.trackmyfix.trackmyfix.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.security.auth.login.AccountNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ⚠️ Excepción para autenticación (Inicio de sesión)
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // ⚠️ Excepción para tipo de usuario no autorizado para realizar una tarea
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.FORBIDDEN);
    }

    // ⚠️ Excepción para cuando un usuario no es encontrado
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // ⚠️ Excepción para datos inválidos en las solicitudes
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    // ⚠️ Excepción para cuando una orden no es encontrada
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // ⚠️ Excepción para cuando el precio es negativo o menor a lo requerido
    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<String> handleInvalidPriceException(InvalidPriceException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    // ⚠️ Excepción genérica para recursos no encontrados
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // ⚠️ Manejo de cualquier otra excepción no específica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> generateErrorResponse(Exception ex, WebRequest request, HttpStatus status) {
        String errorMessage = String.format(
                "%s: %s | Ruta: %s",
                ex.getClass().getSimpleName(), ex.getMessage(), request.getDescription(true)
        );
        return new ResponseEntity<>(errorMessage, status);
    }
}
