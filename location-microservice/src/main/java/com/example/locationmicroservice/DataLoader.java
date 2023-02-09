package com.example.locationmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@Transactional
public class DataLoader {
    @Autowired
    private LocationDAO locationDAO;

    @PostConstruct
    public void init(){
        LocationModel l1 = new LocationModel(0,"Irving", "75063");
        l1 = locationDAO.save(l1);
        System.out.println("Location = "+ l1.toString());

        l1 = locationDAO.save(new LocationModel(0,"Plano", "75024"));
        System.out.println("Location = "+ l1.toString());

        l1 = locationDAO.save(new LocationModel(0,"Dallas", "75204"));
        System.out.println("Location = "+ l1.toString());

        l1 = locationDAO.save(new LocationModel(0,"Irving", "75015"));
        System.out.println("Location = "+ l1.toString());

        l1 = locationDAO.save(new LocationModel(0,"Coppell", "75019"));
        System.out.println("Location = "+ l1.toString());

        l1 = locationDAO.save(new LocationModel(0,"Coppell", "75099"));
        System.out.println("Location = "+ l1.toString());

        l1 = locationDAO.save(new LocationModel(0,"Irving", "75099"));
        System.out.println("Location = "+ l1.toString());
    }
}
