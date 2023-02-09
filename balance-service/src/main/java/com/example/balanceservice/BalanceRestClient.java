package com.example.balanceservice;

import com.example.balanceservice.Model.GeoName;
import com.example.balanceservice.Model.LocationModel;
import com.example.balanceservice.Model.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@AllArgsConstructor
public class BalanceRestClient {

    private RestTemplate restTemplate;

////////////////////////// Geonames.org API /////////////////////
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public class ZipcodeInfoResponse {
//
//        @JsonProperty("postalcodes")
//        private List<GeoName> postalCodes;
//
//        //getter - setter
//    }

    public List<GeoName> getNearestLocationByZipcode(String zipCode, int radius)  {
//        System.out.println("/n"+zipCode+radius+"/n");
        final String uri = "http://api.geonames.org/findNearbyPostalCodesJSON?postalcode="+zipCode+"&country=US&radius="+radius+"&username=thanhhuynh88";

        Map<String, List<Object>> geonames = restTemplate.getForObject(uri, Map.class);
        List<Object> location = geonames.get("postalCodes");

//        String zip = restTemplate.getForObject(uri, String.class);
//        System.out.println("\n+++ "+zip);
        List<GeoName> geoNameList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
//        GeoName geo = new GeoName();
//        try {
//            geo = fromJson(mapper.writeValueAsString(location.get(0)));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("\nCCCCCCC"+geo.getCountryCode());

        for (int i = 0; i < location.size(); i++) {
                try {
                    geoNameList.add(fromJson(mapper.writeValueAsString(location.get(i))));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        return geoNameList;
    }

    public GeoName fromJson(String json) throws JsonProcessingException{
    //    System.out.println("\n****"+json);
        GeoName garima = new ObjectMapper().readValue(json, GeoName.class);
        return garima;
    }


//////////////////////////////////// Product  /////////////////////
    public ProductModel getProductsById(long productId) {
        final String uri = "http://localhost:9091/products/"+productId;
        return restTemplate.getForObject(uri, ProductModel.class);
    }

    public List<ProductModel> getProductsByName(String name) {
        final String uri = "http://localhost:9091/searchProducts/"+name;
        ResponseEntity<List<ProductModel>> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductModel>>(){});
        return responseEntity.getBody();
    }
//////////////////////////////////// Location  /////////////////////
    public LocationModel getLocationById(long locationId) {
        final String uri = "http://localhost:9092/locations/"+locationId;
        return restTemplate.getForObject(uri, LocationModel.class);
    }

    public List<LocationModel> getLocationByName(String name) {
        final String uri = "http://localhost:9092/searchLocationsByName/"+name;
        ResponseEntity<List<LocationModel>> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LocationModel>>(){});
        return responseEntity.getBody();
    }
}
