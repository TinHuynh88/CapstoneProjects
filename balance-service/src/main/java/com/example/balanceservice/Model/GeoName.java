package com.example.balanceservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Indexed;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoName {
    private String adminCode2;
    private String adminCode1;
    private String adminName2;
    private double lng;
    private String distance;
    private String countryCode;
    private String postalCode;
    private String adminName1;
    private String placeName;
    private double lat;

}
