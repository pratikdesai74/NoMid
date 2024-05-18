package com.ecommerce.orderservice.external.decoder;

import com.ecommerce.orderservice.exception.OrderServiceCustomException;
import com.ecommerce.orderservice.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper=new ObjectMapper();

        log.info("response::{}",response);
        log.info("url::{}",response.request().url());
        log.info("request headers::{}",response.request().headers());

        try {
            ErrorResponse errorResponse=
                    objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new OrderServiceCustomException(errorResponse.getErrorCode(), response.status(),errorResponse.getErrorMessage());
        } catch (IOException e) {
            log.info("e ::{}",e.getMessage());
            throw new OrderServiceCustomException("INTERNAL_SERVER_ERROR",500,"Internal Server Error");
        }
    }
}
