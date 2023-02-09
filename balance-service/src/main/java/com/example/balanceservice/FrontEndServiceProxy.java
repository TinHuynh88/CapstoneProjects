package com.example.balanceservice;

import com.example.balanceservice.Model.LocationModel;
import com.example.balanceservice.Model.ProductModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-availability-ui")
public interface FrontEndServiceProxy {

    // PRODUCT
    @GetMapping("/product-microservice/products")
    public List<ProductModel> getProduct();

    @GetMapping("/product-microservice/products/{productId}")
    public ProductModel getProductById(@PathVariable long productId);

    @GetMapping("/product-microservice/searchProducts/{name}")
    public List<ProductModel> getProductsByName(@PathVariable String name);

    // LOCATION
    @GetMapping("/location-microservice/locations")
    public List<LocationModel> getlocation();

    @GetMapping("/location-microservice/locations/{locationId}")
    public LocationModel getLocationById(@PathVariable long locationId);

    @GetMapping("/location-microservice/searchLocationsByZip/{zip}")
    public List<LocationModel> getLocationByZipcode(@PathVariable String zip);

    @GetMapping("/location-microservice/getLocationByLocationName/{locationName}")
    public List<LocationModel> getLocationByLocationName(@PathVariable String locationName);
}



