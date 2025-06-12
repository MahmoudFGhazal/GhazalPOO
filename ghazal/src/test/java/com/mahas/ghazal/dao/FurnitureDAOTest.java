package com.mahas.ghazal.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Furniture furniture = new Furniture();
        furniture.setId(1);

        List<DomainEntity> result = furnitureDAO.query(furniture);
    

        List<Furniture> furnitures = (List<Furniture>) (List<?>) result;

        furniture = furnitures.get(0);
        assertEquals(1, furniture.getId());
    }
}
