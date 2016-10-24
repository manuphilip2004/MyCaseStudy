package com.casestudy.myretail.service;

import com.casestudy.myretail.domain.Product;
import com.casestudy.myretail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Manu on 10/22/2016.
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductInfo(String id) {
        Product product = null;
        product = productRepository.findByTcinId(id);
        return product;
    }
}