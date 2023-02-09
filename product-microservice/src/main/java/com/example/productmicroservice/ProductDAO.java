package com.example.productmicroservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDAO extends JpaRepository<ProductModel, Long> {
    @Query(value = "select * from product_model where (lower(name) like :search"
            + " or lower(dept_name) like :search)", nativeQuery = true)
    Optional<List<ProductModel>> generalSearchProduct(@Param("search") String search);
}
