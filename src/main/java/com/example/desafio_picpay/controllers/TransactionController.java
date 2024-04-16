package com.example.desafio_picpay.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafio_picpay.domain.transaction.Transaction;
import com.example.desafio_picpay.dtos.TransactionDTO;
import com.example.desafio_picpay.services.TransactonService;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    
    @Autowired
    private TransactonService transactonService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception{
        Transaction newTransaction = this.transactonService.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);

    }
}
