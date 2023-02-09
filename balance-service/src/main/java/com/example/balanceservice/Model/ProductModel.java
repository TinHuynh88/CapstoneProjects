package com.example.balanceservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel implements Serializable {
    private long productId;
    private String name;
    private long deptId;
    private String deptName;

}
