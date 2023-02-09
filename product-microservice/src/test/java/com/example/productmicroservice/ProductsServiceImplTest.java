package com.example.productmicroservice;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductsServiceImplTest {

    @Mock
    ProductDAO repository;

    @InjectMocks
    ProductsServiceImpl service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllProduct_ShouldCallRepoFindAll() {
        //arrange
        List<ProductModel> expected = Arrays.asList(
                new ProductModel(1,"Long Sleeves",100,"Shirts"),
                new ProductModel(2,"Short Sleeves",100,"Shirts")
        );
        when(repository.findAll()).thenReturn(expected);

        //act
        List<ProductModel> actual = service.getAllProduct();

        //assert
        Mockito.verify(repository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getProductById_ThrowsProductNotFoundExceptionWhenProuctNotFound() {
        Assertions.assertThrows(ProductNotFoundException.class,
                ()->{ service.getProductById(100l);});
    }

    @Test
    void getProductById_ReturnsProduct() {
        //arrange
        ProductModel expected = new ProductModel(1,"Long Sleeves",100,"Shirts");
        when(repository.findById(1l)).thenReturn(Optional.of(expected));

        //act
        ProductModel actual = service.getProductById(1l);

        //assert
        Mockito.verify(repository).findById(1l);
        assertEquals(expected, actual);
        assertEquals(expected.getProductId(), actual.getProductId());
        assertEquals(expected.getDeptId(), actual.getDeptId());
        assertEquals(expected.getDeptName(), actual.getDeptName());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void generalSearchProduct_ThrowsProductNotFoundExceptionWhenProductNotFound() {
        Assertions.assertThrows(ProductNotFoundException.class,
                ()->{ service.generalSearchProduct("Laptop");});
    }

    @Test
    void generalSearchProduct_ReturnsProduct() {
        //arrange
        List<ProductModel> expected = Arrays.asList(
                new ProductModel(1,"Long Sleeves",100,"Shirts"),
                new ProductModel(2,"Short Sleeves",100,"Shirts")
        );
        when(repository.generalSearchProduct(anyString())).thenReturn(Optional.of(expected));

        //act
        List<ProductModel> actual = service.generalSearchProduct("Sleeves");

        //assert
        Mockito.verify(repository).generalSearchProduct("%sleeves%");
        assertEquals(expected, actual);
    }
}