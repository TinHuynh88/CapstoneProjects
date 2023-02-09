package com.example.balanceservice.Model;

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
public class BalanceModel implements Serializable {
    @Id
    @GeneratedValue
    private long balanceId;
    private long productId;
    private long locationId;
    private int available;
//    @ManyToOne
//    private ProductModel product;
//
//    @ManyToOne
//    private LocationModel location;
}
