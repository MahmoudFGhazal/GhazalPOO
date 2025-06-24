package com.mahas.ghazal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mahas.ghazal.dao.furniture.FurnitureDAO;
import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class FurnitureDAOTest {

    @Autowired
    private FurnitureDAO furnitureDAO;

    @Test
    public void query(){
        //Arrange
        Furniture furniture = new Furniture();
        furniture.setId(1);

        //Act
        List<DomainEntity> result = furnitureDAO.query(furniture);
    
        //Assert
        assertNotNull(result, "O resultado da consulta não pode ser nulo");
        assertFalse(result.isEmpty(), "A lista de resultado não pode estar vazia");

        List<Furniture> furnitures = (List<Furniture>) (List<?>) result;
        furniture = furnitures.get(0);
        assertEquals(1, furniture.getId());
    }
}
