package com.ecommerce.orderservice.exception;


import lombok.Data;

@Data
public class OrderServiceCustomException extends RuntimeException{

    private String errorCode;
    private int status;

    public OrderServiceCustomException(String errorCode,int status,String errorMessage){
        super(errorMessage);
        this.status=status;
        this.errorCode=errorCode;
    }

}
