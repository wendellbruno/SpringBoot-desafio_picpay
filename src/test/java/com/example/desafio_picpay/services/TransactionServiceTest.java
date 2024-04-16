package com.example.desafio_picpay.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.desafio_picpay.domain.user.User;
import com.example.desafio_picpay.dtos.TransactionDTO;
import com.example.desafio_picpay.enums.UserType;
import com.example.desafio_picpay.repositorys.TransactionRepository;


public class TransactionServiceTest {

    @Mock //cria uma classe identica porem com metodos vazios
    private UserService userService;

    @Mock
    private TransactionRepository repository;

    @Mock
    private AuthorizationService authService;

    @Mock
    private NotificationService notificationService;

    @Autowired
    @InjectMocks //inja os mock nas dependencias da classe real 
    private TransactonService transactionService;

    @BeforeEach //inicia antes de todo o teste
    void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Should create transaction successfully when everything is OK")
    void createTransactionCase1() throws Exception {
        User sender = new User(1L, "Wendell", "Bruno", "123456", new BigDecimal(100), UserType.COMMON, "123456", "wendell1");
        User receiver = new User(1L, "Bruno", "Wendell", "123456", new BigDecimal(100), UserType.COMMON, "123457", "wendell2");

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(), any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
        transactionService.createTransaction(request);

        verify(repository, times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(userService, times(1)).saveUser(sender);

        receiver.setBalance(new BigDecimal(20));
        verify(userService, times(1)).saveUser(receiver);

        verify(notificationService, times(1)).sendNotification(sender, "Transação realizada com sucesso");
        verify(notificationService, times(1)).sendNotification(receiver, "Transação recebida com sucesso");
    }

    @Test
    @DisplayName("Should throw Exception when Transaction is not allowed")
    void createTransactionCase2() throws Exception {
        User sender = new User(1L, "Wendell", "Bruno", "123456", new BigDecimal(100), UserType.COMMON, "123456", "wendell1");
        User receiver = new User(1L, "Bruno", "Wendell", "123456", new BigDecimal(100), UserType.COMMON, "123457", "wendell2");

        when(userService.findUserById(1L)).thenReturn(sender);
        when(userService.findUserById(2L)).thenReturn(receiver);

        when(authService.authorizeTransaction(any(), any())).thenReturn(false);

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            TransactionDTO request = new TransactionDTO(new BigDecimal(10), 1L, 2L);
            transactionService.createTransaction(request);
        });

        Assertions.assertEquals("Transação não autorizada", thrown.getMessage());
    }
}
