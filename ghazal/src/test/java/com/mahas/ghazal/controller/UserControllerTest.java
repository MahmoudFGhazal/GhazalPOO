package com.mahas.ghazal.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;

import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.user.User;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
public class UserControllerTest {

    @Autowired
    private UserController controller;

    @Test
    public void getUserTest(){
        //Assert
        String email = "admin@gmail.com";
        String password = "m";

        //Act
        ResponseEntity<?> response = controller.Login(email, password);

        //Arrange
        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof FacadeResponse, "corpo deve ser uma lista");

        FacadeResponse facadeResponse = (FacadeResponse) body;
        System.out.println(facadeResponse.getMessage());
                
    }

    @Test
    public void putUserTest(){
        //Arrange
        User user = new User();
        user.setEmail("a@aa");
        user.setCpf("123.456.789-07");
        user.setName("Jose");
        user.setPassword("oi");

        //Act
        ResponseEntity<?> response = controller.postUser(user);
        
        //Assert
        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof FacadeResponse, "corpo deve ser uma lista");

        FacadeResponse facadeResponse = (FacadeResponse) body;
        System.out.println(facadeResponse.getMessage());
    }

}
