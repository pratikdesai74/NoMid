package com.ecommerce.productservice.service;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.exception.ProductServiceCustomException;
import com.ecommerce.productservice.model.ProductRequest;
import com.ecommerce.productservice.model.ProductResponse;
import com.ecommerce.productservice.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List < Product > getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public long saveProduct(ProductRequest productRequest) {
        log.info("creating new product");
        Product product=Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                    .build();

        Product finalProduct=productRepository.save(product);
        log.info("Product saved");

        return finalProduct.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        Product product= productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("product with given id not found","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse=new ProductResponse();
        BeanUtils.copyProperties(product,productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long id, long quantity) {
        Product product=productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));

        if (product.getQuantity()<quantity)
            throw new ProductServiceCustomException("Product quantity insufficient","INSUFFICIENT_PRODUCT_QUANTITY");

        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Updated product quantity of the product :{}",product.getProductId());
    }

}
