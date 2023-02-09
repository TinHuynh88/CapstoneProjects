package com.example.productmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService{
    @Autowired
    private ProductDAO productDAO;
    private List<ProductModel> prodList;

    @Override
    public List<ProductModel> getAllProduct() {
        return productDAO.findAll();
    }

    @Override
    public ProductModel getProductById(Long productId) {
        Optional<ProductModel> optional = productDAO.findById(productId);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new ProductNotFoundException(productId);
    }

//    @Override
//    public List<ProductModel> getProductByName(String name) {
//
//        productDAO.findAll().forEach( prod -> {
//            if (prod.getName()!=null && prod.getName().equals(name)){
//                this.prodList.add(prod);
//            }
//        });
//        return this.prodList;
//    }
    @Override
    public List<ProductModel> generalSearchProduct(String search) {
        Optional<List<ProductModel>> optional = productDAO.generalSearchProduct("%"+search.toLowerCase()+"%");
        if(optional.isPresent()){
            return optional.get();
        }
        throw new ProductNotFoundException(search);
    }
}
