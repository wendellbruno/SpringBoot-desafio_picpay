package com.example.desafio_picpay.repositorys;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.desafio_picpay.domain.user.User;
import com.example.desafio_picpay.dtos.UserDTO;
import com.example.desafio_picpay.enums.UserType;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;




@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Buscar Usuario com sucesso")
    void findUserByDocumentSuccess(){
        String document = "123456";
        UserDTO data = new UserDTO("Wendell", "Bruno", document, new BigDecimal(10), "test@gmail.com", "44444", UserType.COMMON);
        this.creatUser(data);
        Optional<User> result =  this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }
    @Test
    @DisplayName("Buscar Usuario com erro pois o documento já existe")
    void findUserByDocumentErro(){
        String document = "123456";
        Optional<User> result =  this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User creatUser(UserDTO  data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
    
}
