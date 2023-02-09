package com.example.productmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    private ProductsService service;

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getProduct(){

        return ResponseEntity.ok(service.getAllProduct());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable long id){
        try {
           return ResponseEntity.ok(service.getProductById(id));
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/searchProducts/{search}")
    public ResponseEntity<List<ProductModel>> searchProduct(@PathVariable String search){
        try {
            return ResponseEntity.ok(service.generalSearchProduct(search));
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
