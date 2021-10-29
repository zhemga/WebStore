package com.example.androidstore.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private String image;

    public ProductDTO() {
    }

    public ProductDTO(int id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
}
