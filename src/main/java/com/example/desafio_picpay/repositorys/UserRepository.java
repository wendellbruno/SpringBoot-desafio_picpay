package com.example.desafio_picpay.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafio_picpay.domain.user.User;


public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);

}
