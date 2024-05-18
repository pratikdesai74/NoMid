package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
