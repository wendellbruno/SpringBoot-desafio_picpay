package com.example.desafio_picpay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.desafio_picpay.domain.transaction.Transaction;
import com.example.desafio_picpay.domain.user.User;
import com.example.desafio_picpay.dtos.TransactionDTO;
import com.example.desafio_picpay.repositorys.TransactionRepository;

@Service
public class TransactonService {

    @Autowired
    UserService userService;

    @Autowired
    TransactionRepository repository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthorizationService authorizationService;;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizationService.authorizeTransaction(sender, transaction.value());
        if(!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }
        
        Transaction newTransaction = new Transaction();
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setAmount(transaction.value());
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newTransaction;

    }

 
    
}
