package com.casestudy.myretail.exception;

import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Manu on 10/22/2016.
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Unexpected Server Exception!")
public class ServerException extends RuntimeException  {
    public ServerException() {
        Log logger = LoggerFactory.make();
        logger.error("INTERNAL_SERVER_ERROR");
    }
}
