package com.example.androidstore;

import com.example.androidstore.error.ErrorInstance;

import java.util.List;

import lombok.Data;

@Data
public class AuthErrorMessage {
    ErrorInstance errors;
}
