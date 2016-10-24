package com.casestudy.myretail.controller;

import com.casestudy.myretail.Application;
import com.casestudy.myretail.domain.Product;
import com.casestudy.myretail.repository.ProductRepository;
import com.casestudy.myretail.service.ProductService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Manu on 10/23/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ProductControllerTest {
    private MediaType contentType = APPLICATION_JSON_UTF8;


    private MockMvc mockMvc;
    private Product product;
    private List<Product> productList = new ArrayList<>();

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        productRepository.deleteAll();
        productList.add(productRepository.save(new Product("15117729",100.00, "USD")));
        productList.add(productRepository.save(new Product("13860428", 336.35, "USD")));
        productList.add(productRepository.save(new Product("16483589", 3.00, "USD")));
        productList.add(productRepository.save(new Product("16696652", 310.99, "USD")));
        productList.add(productRepository.save(new Product("16752456", 199.99, "USD")));
        productList.add(productRepository.save(new Product("15643793", 29.99, "USD")));
        productList.add(productRepository.save(new Product("12258793", 29.99, "USD")));
    }

    @Test
    public void getProductInformation () throws Exception {
        mockMvc.perform(get("/myretail/v1/products/13860428"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(13860428)))
                .andExpect(jsonPath("$.current_price.value", is(336.35)))
                .andExpect(jsonPath("$.current_price.currency_code", is("USD")))
                .andExpect(jsonPath("$.name", is("Blu-ray BIG LEBOWSKI, THE Movies")));
    }
    @Test
    public void getProductExist () throws Exception {
        mockMvc.perform(get("/myretail/v1/products/15117729"))
                .andExpect(status().isOk());
    }
    @Test
    public void getProductNotExist () throws Exception {
        mockMvc.perform(get("/myretail/v1/products/16483589"))
                .andExpect(status().isNotFound());
    }

}

