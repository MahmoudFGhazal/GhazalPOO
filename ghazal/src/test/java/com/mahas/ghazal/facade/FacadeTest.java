package com.mahas.ghazal.facade;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

import com.mahas.ghazal.domain.DomainEntity;
import com.mahas.ghazal.domain.furniture.Furniture;
import com.mahas.ghazal.facade.Facade;

@SpringBootTest
@Transactional
public class FacadeTest {
    
    @Autowired
    private Facade facade;


    @Test
    public void queryTest(){
        Furniture furniture = new Furniture();

        List<DomainEntity> result = facade.query(furniture);
        List<Furniture> furnitures = (List<Furniture>) (List<?>) result;

        System.out.println("Moveis");
        furnitures.forEach(f ->
            System.out.println("Furniture: " + f.getModel())
        );
    }
}
