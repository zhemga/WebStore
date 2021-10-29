package com.example.androidstore.dto;

import lombok.Data;

@Data
public class ProductEditDTO {
    private int id;
    private String name;
    private double price;
    private String image;
}
