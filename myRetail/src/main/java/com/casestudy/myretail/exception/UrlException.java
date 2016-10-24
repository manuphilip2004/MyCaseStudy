package com.casestudy.myretail.exception;

import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Manu on 10/22/2016.
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Url not reachable!")
public class UrlException extends RuntimeException{
    public UrlException() {
        Log logger = LoggerFactory.make();
        logger.error("URL Not Reachable");
    }
}
