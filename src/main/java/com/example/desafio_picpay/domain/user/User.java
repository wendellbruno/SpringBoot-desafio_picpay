package com.example.desafio_picpay.domain.user;

import java.math.BigDecimal;

import com.example.desafio_picpay.dtos.UserDTO;
import com.example.desafio_picpay.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String fistName;

    private String lastName;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType usertype;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    public User(UserDTO data) {
        this.fistName = data.fistName();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.usertype = data.userType();
        this.password = data.password();
        this.document = data.document();
        this.email = data.email();
    }

    

    

}
