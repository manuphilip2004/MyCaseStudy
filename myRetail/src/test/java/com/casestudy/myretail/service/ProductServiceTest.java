package com.casestudy.myretail.service;

import com.casestudy.myretail.domain.Product;
import com.casestudy.myretail.repository.ProductRepository;
import org.junit.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Manu on 10/23/2016.
 */
public class ProductServiceTest {
    @Test
    public void testWhenProductIdIsNotEmpty() {
        ProductRepository productsRepositoryMock = mock(ProductRepository.class);
        when(productsRepositoryMock.findOne(any(String.class))).thenReturn(new Product());
        ProductService productService = new ProductService();
        productService.setProductRepository(productsRepositoryMock);
        Product p = productService.getProductInfo("13860428");
    }
}
