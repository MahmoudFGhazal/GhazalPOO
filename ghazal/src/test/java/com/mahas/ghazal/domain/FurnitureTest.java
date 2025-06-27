package com.mahas.ghazal.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mahas.ghazal.dao.furniture.FurnitureDAO;
import com.mahas.ghazal.domain.furniture.Category;
import com.mahas.ghazal.domain.furniture.Furniture;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class FurnitureTest {
    
    @Autowired
    private FurnitureDAO furnitureDAO;

    @Test
    public void CategoriesTest(){
        //Arrange
        Furniture furnitureFilter = new Furniture();
        furnitureFilter.setId(1);

        //Act
        List<DomainEntity> result = furnitureDAO.query(furnitureFilter);
        List<Furniture> furnitures = (List<Furniture>) (List<?>) result;

        //Assert
        assertNotNull(furnitures, "A lista de móveis não pode ser nula");
        assertFalse(furnitures.isEmpty(), "A lista de móveis não pode estar vazia");

        Furniture furniture = furnitures.get(0);
        Set<Category> categories = furniture.getCategories();

        assertNotNull(categories, "A lista de categorias não pode ser nula");
        assertFalse(categories.isEmpty(), "O móvel deve ter pelo menos uma categoria");
        
        System.out.println("Categoria");
        categories.forEach(c ->
            System.out.println("Categoria: " + c.getCategory())
        );
    }

}
