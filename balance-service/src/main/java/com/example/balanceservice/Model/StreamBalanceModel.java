package com.example.balanceservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table
@Indexed
@AllArgsConstructor
@NoArgsConstructor
public class StreamBalanceModel implements Serializable {
    @Id
    @GeneratedValue
    private long streamBalanceId;
    private LocalDateTime date; // uuuu-MM-dd'T'HH:mm:ss.SSSS  like: 2007-12-03T10:15:30
    private long balanceId;
    private long productId;
    private String productName;
    private long locationId;
    private String locationName;
    private String zipcode;
    private int oldAvailable;
    private int newAvailable;
}
