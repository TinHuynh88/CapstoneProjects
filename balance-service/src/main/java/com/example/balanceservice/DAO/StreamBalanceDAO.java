package com.example.balanceservice.DAO;

import com.example.balanceservice.Model.StreamBalanceModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreamBalanceDAO extends CrudRepository<StreamBalanceModel, Long> {
//    @Query(value = "select balance_model.product_id from balance_model "
//            +"join product_model.product_id = balance_model.product_id", nativeQuery = true)
//    List<Object[]> getBalanceDetails();
      List<StreamBalanceModel> findByLocationId(long locationId);
}
