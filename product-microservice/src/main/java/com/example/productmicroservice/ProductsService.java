package com.example.productmicroservice;

import java.util.List;

public interface ProductsService {
    public List<ProductModel> getAllProduct();
    public ProductModel getProductById(Long productId);
 //   public List<ProductModel> getProductByName(String name);
    public List<ProductModel> generalSearchProduct(String search);
}
