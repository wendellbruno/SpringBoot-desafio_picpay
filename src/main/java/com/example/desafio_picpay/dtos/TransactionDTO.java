package com.example.desafio_picpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(
    BigDecimal value,
    Long senderId,
    long receiverId
) {
    
}
