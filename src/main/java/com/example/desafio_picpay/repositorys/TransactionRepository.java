package com.example.desafio_picpay.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio_picpay.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{}
