package com.mahas.ghazal.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.user.favorite.Favorite;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
public class FavoriteControllerTest {

    @Autowired
    private FavoriteController controller;

    @Test
    public void testgetFavorites(){
        //Arrange
        int userId = 1;

        //Act
        ResponseEntity<?> response = controller.getFavorites(userId);

        //Assert
        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof FacadeResponse);

        FacadeResponse facadeResponse = (FacadeResponse) body;
        assertNotNull(facadeResponse.getEntities());

        List<DomainEntity> entities = facadeResponse.getEntities();
        assertFalse(entities.isEmpty(), "A lista de entidades não pode estar vazia");
        assertTrue(entities.get(0) instanceof Favorite);

        List<Favorite> favorites = entities.stream()
                                    .filter(Favorite.class::isInstance)
                                    .map(Favorite.class::cast)
                                    .toList();

        for(Favorite f : favorites){
            System.out.println("Movel: " + f.getFurniture().getId() +  "\nUsuario: " + f.getUser().getId());
        }

    }

    @Test   
    public void testPut(){
        //Arrange
        int userId = 1;
        int furnitureId = 10;

        //Act
        ResponseEntity<?> response = controller.putFavorite(userId,furnitureId);

        //Assert
        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof FacadeResponse);

        FacadeResponse facadeResponse = (FacadeResponse) body;
        assertNull(facadeResponse.getMessage());
    }

    @Test   
    public void testDelete(){
        //Arrange
        int userId = 1;
        int furnitureId = 5;

        //Act
        ResponseEntity<?> response = controller.deleteFavorite(userId,furnitureId);

        //Asserts
        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof FacadeResponse);

        FacadeResponse facadeResponse = (FacadeResponse) body;
        assertNull(facadeResponse.getMessage());     
    }

}
