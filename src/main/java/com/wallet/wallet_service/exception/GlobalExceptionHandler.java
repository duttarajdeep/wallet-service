package com.wallet.wallet_service.exception;

import com.wallet.wallet_service.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> details = ex.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        FieldError::getDefaultMessage));

        ApiResponse<?> body = ApiResponse.error("VALIDATION_FAILED", details.toString());
        return ResponseEntity.badRequest().body(body);
    }

    public ResponseEntity<ApiResponse<?>> handleGeneric(Exception ex) {
        log.error("Unexpected error: ", ex);
        ApiResponse<?> body = ApiResponse.error("INTERNAL_ERROR", "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    public ResponseEntity<ApiResponse<?>> handleIllegalArg(Exception ex) {
        ApiResponse<?> body = ApiResponse.error("BUSINESS_ERROR", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

}
