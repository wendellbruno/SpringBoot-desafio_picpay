package com.example.desafio_picpay.services;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.desafio_picpay.domain.user.User;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    public Boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> auth = restTemplate.getForEntity("https://run.mocky.io/v3/9b89b419-a2f7-4885-aa86-5ddcea24d520", Map.class);
    
        if(auth.getStatusCode() == HttpStatus.OK){
            String message =  (String) auth.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else return false;
    
    }
    
}
