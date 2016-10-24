package com.casestudy.myretail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Manu on 10/22/2016.
 */
public class ProductPrice {
    Double value;
    @JsonProperty("currency_code")
    String currencyCode;

    public ProductPrice() {
    }

    public ProductPrice(Double value, String currencyCode) {

        this.value = value;
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "value=" + value +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
