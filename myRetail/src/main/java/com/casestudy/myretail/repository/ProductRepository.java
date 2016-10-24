package com.casestudy.myretail.repository;

import com.casestudy.myretail.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by Manu on 10/22/2016.
 */
public interface ProductRepository extends MongoRepository<Product, String> {
    public Product findByTcinId(String tcinId);
}
