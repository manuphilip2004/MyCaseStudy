package com.casestudy.myretail.domain;
/**
 * Created by Manu on 10/22/2016.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @Indexed(unique = true)
    public String tcinId;
    public double price;
    public String currency;


    public Product() {
        super();
    }

    public Product(String tcinId, double price, String currency) {
        super();
        this.tcinId = tcinId;
        this.price = price;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTcin() {
        return tcinId;
    }

    public void setTcin(String tcin) {

        this.tcinId = tcin;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Product{" +
                "tcinId='" + tcinId + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
