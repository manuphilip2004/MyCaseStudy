package com.casestudy.myretail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Manu on 10/22/2016.
 */
public class ProductDetail {
    Long id;
    String name;
    @JsonProperty("current_price")
    ProductPrice currentPrice;

    public ProductDetail() {
    }

    public ProductDetail(Long id, String name, ProductPrice currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductPrice getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(ProductPrice currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}

