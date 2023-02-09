package com.example.balanceservice;

import com.example.balanceservice.DAO.BalanceDAO;
import com.example.balanceservice.Model.BalanceModel;
import com.example.balanceservice.Model.GeoName;
import com.example.balanceservice.Model.LocationModel;
import com.example.balanceservice.Model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceServiceImpl implements BalanceService{
    @Autowired
    private BalanceRestClient restClient;

    @Autowired
    private BalanceDAO balanceDAO;

    @Autowired
    private FrontEndServiceProxy proxy;

    private Object[] objects = new Object[8];
    private List<Object[]> listObject;
    private List<LocationModel> locationModels;
    private List<BalanceModel> balanceModels;

    @Override
    public List<Object[]> getBalanceDetails(String name) {

        this.listObject = new ArrayList<>();
        Iterable<BalanceModel> balanceModels = balanceDAO.findAll();

        List<ProductModel> productList = proxy.getProductsByName(name);
       // List<LocationModel> locationList = restClient.get
        balanceModels.forEach( balance -> {
            productList.forEach(product ->{
                if(product.getProductId() == balance.getProductId()) {
                    this.objects = new Object[8];
                    this.objects[0] = product.getProductId();
                    this.objects[1] = product.getName();
                    this.objects[2] = product.getDeptId();
                    this.objects[3] = product.getDeptName();
                    LocationModel location= proxy.getLocationById(balance.getLocationId());
                    this.objects[4] = location.getLocationId();
                    this.objects[5] = location.getLocationName();
                    this.objects[6] = location.getZipcode();
                    this.objects[7] = balance.getAvailable();
                    this.listObject.add(this.objects);
                }
            });
        });
        return listObject;
    }

    @Override
    public List<Object[]> getAllBalanceDetails() {
        this.listObject = new ArrayList<>();
        Iterable<BalanceModel> balanceModels = balanceDAO.findAll();

        balanceModels.forEach( balance -> {
            this.objects = new Object[8];

            ProductModel product = proxy.getProductById(balance.getProductId());
            this.objects[0] = product.getProductId();
            this.objects[1] = product.getName();
            this.objects[2] = product.getDeptId();
            this.objects[3] = product.getDeptName();

            LocationModel location= proxy.getLocationById(balance.getLocationId());
            this.objects[4] = location.getLocationId();
            this.objects[5] = location.getLocationName();
            this.objects[6] = location.getZipcode();
            this.objects[7] = balance.getAvailable();

            this.listObject.add(this.objects);
        });
        return listObject;
    }

    @Override
    public List<GeoName> getNearestLocationByZipcode(String zipCode, int radius) {
        return restClient.getNearestLocationByZipcode(zipCode, radius);
    }

    @Override
    public List<Object[]> getItemsNearestLocation(String item, String zipCode, int radius) {
        List<GeoName> geoNames = getNearestLocationByZipcode(zipCode,radius);
        this.balanceModels = new ArrayList<>();
        this.locationModels = new ArrayList<>();

        geoNames.forEach(geoName -> {
//            System.out.println("\nAAA"+ geoName.toString());
            List<LocationModel> listLocation = proxy.getLocationByZipcode(geoName.getPostalCode());
//            System.out.println("\nlistLocation= " + listLocation.toString());
            listLocation.forEach(loc -> {
                this.locationModels.add(loc);
                this.balanceModels.addAll(balanceDAO.findByLocationId(loc.getLocationId()));
            });
        });

//        System.out.println("\nbalanceModels= " + this.balanceModels.toString());

        if(balanceModels.isEmpty()) return null;

        this.listObject = new ArrayList<>();
    //    List<BalanceModel> balanceModels = balanceDAO.findAll();

        List<ProductModel> productList = proxy.getProductsByName(item);
        // List<LocationModel> locationList = restClient.get
        balanceModels.forEach( balance -> {
            productList.forEach(product ->{
                if(product.getProductId() == balance.getProductId()) {
                    this.objects = new Object[8];
                    this.objects[0] = product.getProductId();
                    this.objects[1] = product.getName();
                    this.objects[2] = product.getDeptId();
                    this.objects[3] = product.getDeptName();
                    LocationModel location= getLocationFromList(balance.getLocationId());
                    this.objects[4] = location.getLocationId();
                    this.objects[5] = location.getLocationName();
                    this.objects[6] = location.getZipcode();
                    this.objects[7] = balance.getAvailable();
                    this.listObject.add(this.objects);
                }
            });
        });
        return listObject;
    }

    @Override
    public List<Object[]> getItemsByLocationName(String locationName) {
        this.listObject = new ArrayList<>();
        Iterable<BalanceModel> balanceModels = balanceDAO.findAll();

        List<LocationModel> locationList = proxy.getLocationByLocationName(locationName);
        balanceModels.forEach( balance -> {
            locationList.forEach(location ->{
                if(location.getLocationId() == balance.getLocationId()) {
                    this.objects = new Object[11];

                    ProductModel product= proxy.getProductById(balance.getProductId());
                    this.objects[0] = product.getProductId();
                    this.objects[1] = product.getName();
                    this.objects[2] = product.getDeptId();
                    this.objects[3] = product.getDeptName();

                    this.objects[4] = location.getLocationId();
                    this.objects[5] = location.getLocationName();
                    this.objects[6] = location.getZipcode();
                    this.objects[7] = balance.getAvailable();
                    this.objects[8] = balance.getBalanceId();
                    this.objects[9] = 0; // use for new quanlity to add to availability
                    this.objects[10] = true; // use for diasable the input field
                    this.listObject.add(this.objects);
                }
            });
        });
        return listObject;
    }

    @Override
    public Object[] udpateOneProduct(Object[] product) {
        Optional<BalanceModel> balanceModel = balanceDAO.findById(Long.parseLong(product[8].toString()));
        product[7] = Integer.parseInt(product[7].toString()) + Integer.parseInt(product[9].toString());
        balanceModel.get().setAvailable(Integer.parseInt(product[7].toString()));
        balanceDAO.save(balanceModel.get());
        product[9] = 0; // use for new quanlity to add to availability
        product[10] = true;  // use for diasable the input field
        return product;
    }

    @Override
    public List<Object[]> udpateAllProduct(List<Object[]> productList) {
        productList.forEach(item -> {
            udpateOneProduct(item);
        });
        return getItemsByLocationName(productList.get(0)[5].toString());
    }


    public LocationModel getLocationFromList(long locationId){
        final LocationModel[] locationModel = {new LocationModel()};
        this.locationModels.forEach(l -> {
            if(l.getLocationId() == locationId){
                locationModel[0] = l;
            }
                return;
        });
        return locationModel[0];
    }

}
