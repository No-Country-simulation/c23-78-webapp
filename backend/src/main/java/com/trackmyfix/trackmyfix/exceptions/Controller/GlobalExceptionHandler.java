package com.trackmyfix.trackmyfix.exceptions.Controller;

import com.trackmyfix.trackmyfix.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ⚠️ Excepción para autenticación (Inicio de sesión)
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // ⚠️ Excepción para tipo de usuario no autorizado para realizar una tarea
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String,String>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.FORBIDDEN);
    }

    // ⚠️ Excepción para cuando un usuario no es encontrado
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // ⚠️ Excepción para datos inválidos en las solicitudes
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Map<String,String>> handleInvalidDataException(InvalidDataException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    // ⚠️ Excepción para cuando una orden no es encontrada
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // ⚠️ Excepción para cuando el precio es negativo o menor a lo requerido
    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<Map<String,String>> handleInvalidPriceException(InvalidPriceException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    // ⚠️ Excepción para cuando se intenta actualizar una entidad inactiva
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String,String>> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    // ⚠️ Excepción genérica para recursos no encontrados
    @ExceptionHandler({ResourceNotFoundException.class, MovementNotFoundException.class})
    public ResponseEntity<Map<String,String>> handleNotFoundExceptions(Exception ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.NOT_FOUND);
    }

    // ⚠️ Excepción Token expirado
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<Map<String,String>> handleExpiredTokenException(InsufficientAuthenticationException ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.UNAUTHORIZED);
    }

    // ⚠️ Manejo de cualquier otra excepción no específica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGlobalException(Exception ex, WebRequest request) {
        return generateErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String,String>> generateErrorResponse(Exception ex, WebRequest request, HttpStatus status) {
        Map<String, String> resp = new HashMap<>();
        resp.put(ex.getClass().getSimpleName(), ex.getMessage());
        resp.put("uri", request.getDescription(true));
        return new ResponseEntity<>(resp, status);
    }
}
