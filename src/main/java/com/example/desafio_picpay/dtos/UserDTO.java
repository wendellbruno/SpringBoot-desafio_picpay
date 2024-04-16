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
    
}
