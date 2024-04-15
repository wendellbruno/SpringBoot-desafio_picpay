package com.example.desafio_picpay.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import com.example.desafio_picpay.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity(name = "transactions")
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToAny
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToAny
    @JoinColumn(name = "receiver_id")
    private User receiver;
    
    
    private LocalDateTime timestamp;
}
