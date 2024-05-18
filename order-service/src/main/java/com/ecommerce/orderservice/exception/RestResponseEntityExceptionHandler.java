package com.ecommerce.orderservice.exception;

import com.ecommerce.orderservice.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(OrderServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleOrderServiceException(OrderServiceCustomException orderServiceCustomException){
        ErrorResponse errorResponse=ErrorResponse.builder()
                .errorCode(orderServiceCustomException.getErrorCode())
                .errorMessage(orderServiceCustomException.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(orderServiceCustomException.getStatus()));
    }
}
