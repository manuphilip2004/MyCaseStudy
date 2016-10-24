package com.casestudy.myretail.exception;

import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Manu on 10/22/2016.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Product Not Found!")
public class ProductNotFoundException extends RuntimeException  {
    public ProductNotFoundException() {
        Log logger = LoggerFactory.make();
        logger.error("PRODUCT NOT_FOUND");
    }
}
