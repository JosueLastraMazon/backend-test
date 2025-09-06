package com.neology.estacionamiento.controller;

import com.neology.estacionamiento.model.GenericErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobaExceptionHandlerController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<GenericErrorResponse> handleCannotGetJdbcConnectionException(Exception e, HttpServletRequest request) {
        log.error("Error occurred while trying to connect to database:", request.getRequestURL().toString(), e);
        GenericErrorResponse errorResponse = new GenericErrorResponse("", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage(), request.getRequestURL().toString());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
