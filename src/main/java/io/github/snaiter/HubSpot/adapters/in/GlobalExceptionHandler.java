package io.github.snaiter.HubSpot.adapters.in;

import io.github.snaiter.HubSpot.domain.exceptions.HubspotGatewayException;
import io.github.snaiter.HubSpot.domain.exceptions.TokenCacheNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenCacheNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleTokenCacheNotValid(TokenCacheNotValidException ex) {
        return getMapResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HubspotGatewayException.class)
    public ResponseEntity<Map<String, Object>> handleHubspotGatewayException(HubspotGatewayException ex) {
        return getMapResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, Object>> getMapResponseEntity(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status);
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }
}
