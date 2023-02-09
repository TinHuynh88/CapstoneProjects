package com.example.locationmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationDAO locationDAO;
    private List<LocationModel> prodList;

    @Override
    public List<LocationModel> getAllLocation() {
        return locationDAO.findAll();
    }

    @Override
    public LocationModel getLocationById(Long locationId) {
        return locationDAO.findById(locationId).get();
    }

    @Override
    public List<LocationModel> getLocationByLocationName(String locationName) {
        return locationDAO.findByLocationName(locationName);
    }

    @Override
    public List<LocationModel> searchLocationByName(String search) {
        return locationDAO.searchLocationByName("%"+search.toLowerCase()+"%");
    }

    @Override
    public List<LocationModel> searchLocationByZipcode(String search) {
        return locationDAO.searchLocationByZipcode(search+"%");
    }
}
