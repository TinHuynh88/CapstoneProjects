package com.example.locationmicroservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDAO extends JpaRepository<LocationModel, Long> {
    @Query(value = "select * from location_model where (lower(location_name) like :search)", nativeQuery = true)
    List<LocationModel> searchLocationByName(@Param("search") String search);

    @Query(value = "select * from location_model where (lower(zipcode) like :search)", nativeQuery = true)
    List<LocationModel> searchLocationByZipcode(@Param("search") String search);

    List<LocationModel> findByLocationName(String locationName);
}
