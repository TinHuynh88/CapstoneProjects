package com.example.productmicroservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @InjectMocks
    ProductController controller;

    @Mock
    ProductsService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getProduct_CallGetAllProductFromService() {
        //arrange
        List<ProductModel> expected = Arrays.asList(
                new ProductModel(1,"Long Sleeves",100,"Shirts"),
                new ProductModel(2,"Short Sleeves",100,"Shirts")
        );
        when(service.getAllProduct()).thenReturn(expected);

        //act
        ResponseEntity<List<ProductModel>> response = controller.getProduct();

        //assert
        Mockito.verify(service).getAllProduct();
        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getProductById_ShouldReturnError404_WhenProductNotFound() {
        //Arrang
        when(service.getProductById(anyLong())).thenThrow(new ProductNotFoundException(100L));

        //Act
        ResponseEntity<ProductModel> response = controller.getProductById(100L);

        //assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void getProductById_ReturnsProduct() {
        //arrange
        ProductModel expected = new ProductModel(1,"Long Sleeves",100,"Shirts");
        when(service.getProductById(anyLong())).thenReturn(expected);

        //act
        ResponseEntity<ProductModel> response = controller.getProductById(1l);

        //assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        assertEquals(expected.getProductId(), response.getBody().getProductId());
        assertEquals(expected.getDeptId(), response.getBody().getDeptId());
        assertEquals(expected.getDeptName(), response.getBody().getDeptName());
        assertEquals(expected.getName(), response.getBody().getName());
    }

    @Test
    void searchProduct_ShouldReturnError404_WhenProductNotFound() {
        //Arrang
        when(service.generalSearchProduct(anyString())).thenThrow(new ProductNotFoundException("Laptop"));

        //Act
        ResponseEntity<List<ProductModel>> response = controller.searchProduct("Laptop");

        //assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void searchProduct_ReturnsProduct() {
        //arrange
        List<ProductModel> expected = Arrays.asList(
                new ProductModel(1,"Long Sleeves",100,"Shirts"),
                new ProductModel(2,"Short Sleeves",100,"Shirts")
        );
        when(service.generalSearchProduct(anyString())).thenReturn(expected);

        //act
        ResponseEntity<List<ProductModel>>  response = controller.searchProduct("Sleeves");

        //assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }
}