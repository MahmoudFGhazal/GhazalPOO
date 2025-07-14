package com.mahas.ghazal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;

import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.Review;
import com.mahas.ghazal.domain.user.User;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
public class ReviewControllerTest {
    
    @Autowired
    private ReviewController controller;

    @Test
    public void putTest(){
        //Arrange
        User user = new User();
        user.setId(1);

        Furniture furniture = new Furniture();
        furniture.setId(1);

        Review review = new Review();
        review.setRating(2.0);
        review.setComment("o33i");
        review.setUser(user);
        review.setFurniture(furniture);
        
        //Act
        ResponseEntity<?> response = controller.putReview(review);
        
        //Assert
        assertNotNull(response, "resposta Não pode ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Esperado status 200 OK");
    }

    @Test
    public void getByFurniture(){
        //Arrange

        //Act
        ResponseEntity<?> response = controller.getTotalRating(1);

        //Assert
        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");

        assertTrue(body instanceof FacadeResponse, "corpo precisa ser uma resposta");
        FacadeResponse facadeResponse = (FacadeResponse) body;

        assertTrue(facadeResponse.getEntities() instanceof List, "corpo deve ser uma lista");
        List<?> list = (List<?>) facadeResponse.getEntities();

        if(!list.isEmpty()){       
            for(Object item : list){
                assertTrue(item instanceof Review, "Todos os itens devem ser instâncias de Furniture");
                System.out.println(item.getClass());
            }
        }

    } 
}
