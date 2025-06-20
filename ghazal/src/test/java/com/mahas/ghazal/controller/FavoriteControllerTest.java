package com.mahas.ghazal.controller;

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
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.Favorite;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
public class FavoriteControllerTest {

    @Autowired
    private FavoriteController controller;

    @Test
    public void testByUser(){
        ResponseEntity<?> response = controller.getFavorites(1);

        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof FacadeResponse);

        FacadeResponse facadeResponse = (FacadeResponse) body;

        assertNotNull(facadeResponse.getEntities());

        List<DomainEntity> entities = facadeResponse.getEntities();
        assertTrue(entities.get(0) instanceof Favorite);
        Favorite favorite = (Favorite) entities.get(0);

        for(Furniture f : favorite.getFurnitures()){
            System.out.println(f.getModel());
        }
        System.out.println(favorite.getFurnitures());


        
    }

    @Test   
    public void testPut(){
        ResponseEntity<?> response = controller.putFavorite(1,10);

        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof FacadeResponse);

        FacadeResponse facadeResponse = (FacadeResponse) body;
        assertNull(facadeResponse.getMessage());
        
    }

    @Test   
    public void testDelete(){
        ResponseEntity<?> response = controller.deleteFavorite(1,3);

        assertNotNull(response, "resposta Não pode ser nula");

        Object body = response.getBody();
        assertNotNull(body, "corpo Não pode ser nula");
        assertTrue(body instanceof FacadeResponse);

        FacadeResponse facadeResponse = (FacadeResponse) body;
        assertNull(facadeResponse.getMessage());
        
    }

}
