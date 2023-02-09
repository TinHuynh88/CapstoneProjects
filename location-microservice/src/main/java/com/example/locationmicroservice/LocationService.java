package com.example.locationmicroservice;

import java.util.List;

public interface LocationService {
    List<LocationModel> getAllLocation();
    LocationModel getLocationById(Long locationId);
    List<LocationModel> getLocationByLocationName(String locationName);
    List<LocationModel> searchLocationByName(String search);
    List<LocationModel> searchLocationByZipcode(String search);
}
