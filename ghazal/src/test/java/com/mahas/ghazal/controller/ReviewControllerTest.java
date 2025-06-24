package com.mahas.ghazal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;

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
        review.setRating(3.0);
        review.setComment("oi");
        review.setUser(user);
        review.setFurniture(furniture);

        //Act
        ResponseEntity<?> response = controller.putReview(review);
        
        //Assert
        assertNotNull(response, "resposta Não pode ser nula");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Esperado status 200 OK");
    }
}
