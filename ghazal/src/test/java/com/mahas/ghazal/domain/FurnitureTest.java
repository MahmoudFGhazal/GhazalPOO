package com.mahas.ghazal.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mahas.ghazal.dao.furniture.FurnitureDAO;
import com.mahas.ghazal.domain.furniture.Furniture;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class FurnitureTest {
    
    @Autowired
    private FurnitureDAO furnitureDAO;

    @Test
    public void CategoriesTest(){
        Furniture furniture = new Furniture();
        furniture.setId(1);

        List<DomainEntity> result = furnitureDAO.query(furniture);
        List<Furniture> furnitures = (List<Furniture>) (List<?>) result;

        furniture = furnitures.get(0);
        
        System.out.println("Categoria");
        furniture.getCategories().forEach(c ->
            System.out.println("Categoria: " + c.getCategory())
        );
    }

}
