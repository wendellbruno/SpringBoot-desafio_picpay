package com.example.desafio_picpay.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafio_picpay.domain.user.User;
import com.example.desafio_picpay.dtos.UserDTO;
import com.example.desafio_picpay.enums.UserType;
import com.example.desafio_picpay.repositorys.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amout) throws Exception {
        if (sender.getUsertype() != UserType.COMMON) {
            throw new Exception("Usuario do tipo Lojista não pode realiar transação");
        }
        if (sender.getBalance().compareTo(amout) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }


    public User createUser(UserDTO data) throws Exception{

        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;

    }

    public List<User> getAllUsers(){
        List<User> users = this.repository.findAll();
        return users;

    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    public void saveUser(User user) throws Exception {
        this.repository.save(user);
    }

}
