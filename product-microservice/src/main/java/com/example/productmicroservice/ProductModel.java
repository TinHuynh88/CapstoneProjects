package com.example.productmicroservice;

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
@TableGenerator(name="product_gen", initialValue = 100000, allocationSize = 1)
public class ProductModel implements Serializable {
    @Id
    @GeneratedValue(generator = "product_gen")
    private long productId;
    private String name;
    private long deptId;
    private String deptName;

}
