//This is your model. This file should only contain getters and setters, along with the variables your struct uses.
//DONT FORGET THE CONSTRUCTOR! Getters and setters must be setup to match the name exactly
//or else you will have many errors.
package edu.csumb.Webstore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;


@Document(collection = "Product")
public class Product
{
    @ApiModelProperty(required = false, hidden = true)
    @Id
    private String id;
    private String name;
    private String imageURL;
    private Double price;
    private Integer stock;
    private String description;


    public Product( String name, String imageURL, Double price, Integer stock, String description){
        this.name = name; 
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.stock = stock;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    public String getImageURL(){
        return imageURL;
    }

    public Double getPrice(){
        return price;
    }

    public Integer getStock(){
        return stock;
    }

    public String getDescription(){
        return this.description;
    }

    public void setStock(Integer newAmount){
        if(newAmount < 0)
            this.stock = 0;
        else
            this.stock = newAmount;
    }

}