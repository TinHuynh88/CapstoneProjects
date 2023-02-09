package com.example.locationmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin
@RestController
public class LocationController {
    @Autowired
    private LocationService service;

    @GetMapping("/locations")
    public List<LocationModel> getProduct(){

        return service.getAllLocation();
    }

    @GetMapping("/locations/{id}")
    public LocationModel getProductById(@PathVariable long id){
        try {
            return service.getLocationById(id);
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/getLocationByLocationName/{locationName}")
    public List<LocationModel> getLocationByLocationName(@PathVariable String locationName){
        try {
            return service.getLocationByLocationName(locationName);
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/searchLocationsByName/{search}")
    public List<LocationModel> searchLocationByName(@PathVariable String search){
        try {
            return service.searchLocationByName(search);
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/searchLocationsByZip/{search}")
    public List<LocationModel> searchLocationByZip(@PathVariable String search){
        try {
            return service.searchLocationByZipcode(search);
        } catch (Exception e){
            return null;
        }
    }
}
