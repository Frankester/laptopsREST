package com.example.ejerciciossaludoREST.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ApiModel("Laptop")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Laptop identifier")
    private Long id;
    @ApiModelProperty("screen resolution")
    private String ScreenSize;
    @ApiModelProperty("Model of Laptop")
    private String model;
    @ApiModelProperty("price of Laptop")
    private Double price;
    @ApiModelProperty("is available stock")
    private Boolean avalible;

    public Laptop() {
    }

    public Laptop(Long id, String screen, String model, Double price, Boolean avalible) {
        this.id = id;
        ScreenSize = screen;
        this.model = model;
        this.price = price;
        this.avalible = avalible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScreenSize() {
        return ScreenSize;
    }

    public void setScreenSize(String screenSize) {
        ScreenSize = screenSize;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvalible() {
        return avalible;
    }

    public void setAvalible(Boolean avalible) {
        this.avalible = avalible;
    }
}
