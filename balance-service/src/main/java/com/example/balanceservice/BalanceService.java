package com.example.balanceservice;

import com.example.balanceservice.Model.GeoName;

import java.util.List;

public interface BalanceService {
    public List<Object[]> getBalanceDetails(String name);
    public List<Object[]> getAllBalanceDetails();
    public List<GeoName> getNearestLocationByZipcode(String zipCode, int radius);
    public List<Object[]> getItemsNearestLocation(String item, String zipCode, int radius);
    public List<Object[]> getItemsByLocationName(String locationName);
    public Object[] udpateOneProduct(Object[] product);
    public List<Object[]> udpateAllProduct(List<Object[]> productList);
  //  public boolean updateAvailabilityIfBelow10(List<Truck> trucks);
}
