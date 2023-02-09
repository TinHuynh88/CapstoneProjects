package com.example.locationmicroservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@TableGenerator(name="location_gen", initialValue = 200, allocationSize = 1)
public class LocationModel  implements Serializable {
    @Id
    @GeneratedValue(generator = "location_gen")
    private long locationId;
    private String locationName;
    private String zipcode;
}
