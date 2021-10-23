package com.example.androidstore.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String middleName;
    private String phone;
    private String image;
}
