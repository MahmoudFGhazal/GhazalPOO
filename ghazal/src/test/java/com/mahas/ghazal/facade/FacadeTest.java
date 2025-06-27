package com.mahas.ghazal.facade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import jakarta.transaction.Transactional;

import com.mahas.ghazal.domain.FacadeRequest;
import com.mahas.ghazal.domain.FacadeResponse;
import com.mahas.ghazal.domain.TypeRequest;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.domain.user.Favorite;
import com.mahas.ghazal.domain.user.User;
import com.mahas.ghazal.domain.furniture.Category;

@SpringBootTest
@Transactional
@Commit
public class FacadeTest {
    
    @Autowired
    private Facade facade;


    @Test
    public void queryTest(){
        //Arrange
        Furniture furniture = new Furniture();
        Category category = new Category();
        category.setId(3);
        Set<Category> categories = new HashSet();
        categories.add(category);
        furniture.setCategories(categories);

        FacadeRequest request = new FacadeRequest();
        request.setEntity(furniture);
        request.setTypeRequest(TypeRequest.GET);

        //Act
        FacadeResponse result = facade.FacadeController(request);

        //Assert
        assertNotNull(result, "O resultado da consulta não pode ser nulo");
        assertNotNull(result.getEntities(), "A lista de entidades não pode ser nula");
        assertFalse(result.getEntities().isEmpty(), "A lista de entidades não pode estar vazia");

        List<Furniture> furnitures = (List<Furniture>) (List<?>) result.getEntities();

        System.out.println("Moveis");
        furnitures.forEach(f ->
            System.out.println("Furniture: " + f.getModel())
        );
    }

    @Test
    public void deleteTest(){
        //Arrange
        User user = new User();
        user.setId(1);

        Furniture furniture = new Furniture();
        furniture.setId(3);

        Set<Furniture> furnitures = new HashSet<>();
        furnitures.add(furniture);
        
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setFurnitures(furnitures);

        FacadeRequest request = new FacadeRequest();
        request.setEntity(favorite);
        request.setTypeRequest(TypeRequest.DELETE);

        //Act
        FacadeResponse result = facade.FacadeController(request);

        //Assert
        assertNotNull(result, "O resultado da consulta não pode ser nulo");
        assertNotNull(result.getEntities(), "A lista de entidades não pode ser nula");
        assertFalse(result.getEntities().isEmpty(), "A lista de entidades não pode estar vazia");
    }
}
