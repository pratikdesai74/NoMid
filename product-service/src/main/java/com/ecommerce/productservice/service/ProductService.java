package com.ecommerce.productservice.service;


import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.model.ProductRequest;
import com.ecommerce.productservice.model.ProductResponse;

import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();
    long saveProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long id, long quantity);
}
