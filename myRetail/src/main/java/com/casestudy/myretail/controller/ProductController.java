package com.casestudy.myretail.controller;

import com.casestudy.myretail.domain.Product;
import com.casestudy.myretail.domain.ProductDetail;
import com.casestudy.myretail.domain.ProductPrice;
import com.casestudy.myretail.exception.ProductNotFoundException;
import com.casestudy.myretail.exception.ServerException;
import com.casestudy.myretail.exception.UrlException;
import com.casestudy.myretail.repository.ProductRepository;
import com.casestudy.myretail.service.ProductService;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


/**
 * Created by Manu on 10/20/2016.
 */
@RestController
public class ProductController {

    public final static Log log = LoggerFactory.make();

    @Autowired
    public ProductRepository productRepository;
    public ProductRepository getProductRepository() {
        return this.productRepository;
    }

    @Autowired
    ProductService productService;
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void intializeDatabase() {
        log.info("Initializing the data base....");
        productRepository.deleteAll();
        productRepository.save(new Product("15117729",100.00, "USD"));
        productRepository.save(new Product("13860428", 336.35, "USD"));
        productRepository.save(new Product("16483589", 3.00, "USD"));
        productRepository.save(new Product("16696652", 310.99, "USD"));
        productRepository.save(new Product("16752456", 199.99, "USD"));
        productRepository.save(new Product("15643793", 29.99, "USD"));
        productRepository.save(new Product("12258793", 29.99, "USD"));

    }

   @RequestMapping(value = "myretail/v1/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   ProductDetail getProductDetail(@PathVariable(value = "id") String id) {
       log.info("Scanning database for Product information");
       Product product = productService.getProductInfo(id);
       if (product == null) throw new ProductNotFoundException();
       log.info("Invoking api.target.com for Product Name");
       String productName = getProductname(id);
       /* Build the response */
       ProductDetail productDetail = new ProductDetail();
       ProductPrice productPrice = new ProductPrice();
       productPrice.setCurrencyCode(product.getCurrency());
       productPrice.setValue(product.getPrice());
       productDetail.setId(Long.parseLong(product.getTcin()));
       productDetail.setName(productName);
       productDetail.setCurrentPrice(productPrice);
       log.info(productDetail.toString());
       return productDetail;
   }

    public String getProductname(String id) {
        String productName = null;
        String endPoint ="https://api.target.com/products/v3/";
        String url =endPoint + id + "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
        log.info(url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> productResponse = null;
        try {
            productResponse = restTemplate.getForEntity(url, String.class);
        }
        catch (Exception e){
            log.error(e.toString());
            throw new UrlException();
        }
        if( productResponse.getStatusCode() == HttpStatus.OK) {
            try {
                JSONObject jsonResponse = new JSONObject(productResponse.getBody().toString());
                JSONObject jsonRootElement = jsonResponse.getJSONObject("product_composite_response");
                JSONArray itemsArray = jsonRootElement.getJSONArray("items");
                JSONObject itemObject = itemsArray.getJSONObject(0);
                productName = itemObject.getString("general_description").toString();
            }
            catch (JSONException e) {
                log.error("Exception Caught :" + e.toString());
                throw new ProductNotFoundException();
            }
        }
        else if (productResponse.getStatusCode() == HttpStatus.NOT_FOUND)
            throw new ProductNotFoundException();
        else
            throw new ServerException();
        return productName;
    }
}
