package com.example.productmicroservice;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(long id) {
        super("Product id = "+ id +" not found");
    }
    public ProductNotFoundException(String name) {
        super("Product name = "+ name +" not found");
    }
}
