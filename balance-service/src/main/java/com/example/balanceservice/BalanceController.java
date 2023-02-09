package com.example.balanceservice;

import com.example.balanceservice.Model.GeoName;
import com.example.balanceservice.Model.LocationModel;
import com.example.balanceservice.Model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@RestController
public class BalanceController {
//    @Autowired
//    private BalanceDAO dao;

    @Autowired
    private BalanceService service;

    @GetMapping("/availabilityItem/{search}")
    public List<Object[]> getBalanceDetails(@PathVariable String search){
        try {
            return service.getBalanceDetails(search);
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/availabilityItem")
    public List<Object[]> getAllBalanceDetails(){
        try {
            return service.getAllBalanceDetails();
        } catch (Exception e){
            return null;
        }
    }
    @GetMapping("/items/item={item}&zip={zipCode}&radius={radius}")
    public List<Object[]> getItemNearestLocationByZipcode(@PathVariable String item, @PathVariable String zipCode, @PathVariable int radius){
        try {
            List<Object[]> geoNameList = service.getItemsNearestLocation(item,zipCode, radius);
//            System.out.println("\nControl: "+ geoNameList.get(0));
            return geoNameList;
        } catch (Exception e){
            return null;
        }
    }
    @GetMapping("/items/locationName={locationName}")
    public List<Object[]> getBalanceDetailsByLocationName(@PathVariable String locationName){
        try {
            return service.getItemsByLocationName(locationName);
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/getAllLocation")
    public List<LocationModel> getAllLocation(){
        try {
            return proxy.getlocation();
        } catch (Exception e){
            return null;
        }
    }

    @PutMapping("/updateOneProduct")
    public Object[] updateOneProduct(@RequestBody Object[] product){
        try {
            return service.udpateOneProduct(product);
        } catch (Exception e){
            return null;
        }
    }

    @PutMapping("/updateAllProduct")
    public List<Object[]> updateAllProduct(@RequestBody List<Object[]> products){
        try {
            return service.udpateAllProduct(products);
        } catch (Exception e){
            return null;
        }
    }
    ////////////////TEST
    @Autowired
    private FrontEndServiceProxy proxy;

    @GetMapping("/a")
    public List<ProductModel> products(){
        try {
            return proxy.getProduct();
        } catch (Exception e){
            return null;
        }
    }

}
