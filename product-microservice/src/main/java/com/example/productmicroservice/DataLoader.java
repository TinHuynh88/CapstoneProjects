package com.example.productmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@Transactional
public class DataLoader {
    @Autowired
    private ProductDAO productDAO;

    @PostConstruct
    public void init(){
        ProductModel p1 = new ProductModel(0,"Long Sleeves",100,"Shirts");
        p1 = productDAO.save(p1);

        ProductModel p2 = new ProductModel(0,"Short Sleeves",100,"Shirts");
        p2 = productDAO.save(p2);

        System.out.println("Product = "+ p1.toString());
        System.out.println("Product = "+ p2.toString());

    }
}
