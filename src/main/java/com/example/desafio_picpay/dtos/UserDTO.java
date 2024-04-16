package com.example.desafio_picpay.dtos;

import java.math.BigDecimal;

import com.example.desafio_picpay.enums.UserType;

public record UserDTO(
    String fistName,
    String lastName,
    String email,
    String password,
    String document,
    BigDecimal balance,
    UserType userType
) {

    public UserDTO(String string, String string2, String document2, BigDecimal bigDecimal, String string3,
            String string4, UserType common) {
                this(string, string2, string3, string4, document2, bigDecimal, common);
    }}
