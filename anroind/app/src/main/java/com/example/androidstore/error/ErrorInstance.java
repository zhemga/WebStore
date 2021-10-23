package com.example.androidstore.error;

import java.util.List;

import lombok.Data;

@Data
public class ErrorInstance {
    List<String> email;
    List<String> phone;
    List<String> password;
}
